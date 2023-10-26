const User = require('../models/user.model');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const mailer = require('../utils/mailer');
const validationRegister = require('../utils/validateRegister');
const validationLogin = require('../utils/validateLogin');


let refreshTokens = [];


const registerUser = async(req,res)=>{

    //check if all info is provided
    const { errors, isValid } = validationRegister(req.body)

    if (!isValid) {
        console.log(errors);
        return res.status(400).json(errors)
    }

    //check if user exists
    User.findOne({email:req.body.email})
        .then(user=>{
            if(user)
            {
                return res.status(400).json({error:'Email already in use!'});
            }

            else
            {
                const newUser = new User({
                   firstName:req.body.firstName,
                   lastName:req.body.lastName,
                    userName:req.body.userName,
                    email:req.body.email,
                    password:req.body.password,
                 //   birthDate:req.body.birthDate,
                //    state:req.body.state,
                 //   img:'/uploadedImages/'+req.files[0].filename,
                 //   phone:req.body.phone,
                 //   gender:req.body.gender,

                });
                //encryption of password

                bcrypt.genSalt(10,(err,salt)=>{
                    bcrypt.hash(newUser.password,salt,(err,hash)=>{
                        if(err)
                        {throw err;}

                        newUser.password = hash;
                        User.create(newUser)
                            .then(async u =>{
                                mailer(u)
                                res.status(200).json({error:"account registered successfully! Please check your email for the verification message before logging in!"})
                            })
                    })
                })
            }
        })

}


const login = async(req,res)=>{

    const {email,password}= req.body;

    const {errors,isValid}=validationLogin(req.body);

    if(!isValid){
        return res.status(400).json({error:errors});
    }

    else{
        const userFound = await User.findOne({email:email});

        if (!userFound){
            return res.status(400).json({error:"There is no user with this email!"})
        }

        if (userFound.activeUser ==0)
        {
            return res.status(400).json({error:"This account has been suspended for inactivity"})
        }

        if (userFound.confirm == 0)
        {
            return res.status(400).json({error:"You need to confirm your account first! please check your emails."})
        }

        let isMatch = await bcrypt.compare(req.body.password,userFound.password)

        if(!isMatch)
        {
            return res.status(401).json({error:"Incorrect password"})
        }

        const accessToken = jwt.sign({
            email:userFound.email,id:userFound._id, role:userFound.role
        },
            process.env.ACCESS_TOKEN_SECRET,
            {
                expiresIn: "50000s",
            }
        );

        const refreshToken = jwt.sign({
            email:email,
        },
            process.env.REFRESH_TOKEN_SECRET,
            {
                expiresIn: "1y",
            });

        refreshTokens.push(refreshToken);

        console.log("login",refreshTokens)

        return res.cookie("access_token",accessToken,{httpOnly:true}).status(200).json({
            accessToken,
            refreshToken,
            user:userFound
        });

/*        const token =jwt.sign({
            id:userFound._id, role:userFound.role
        },
            process.env.JWT);
        const {password,role, ...otherDetails} = userFound._doc;
       return res.cookie("access_token",token,{httpOnly:true}).status(200).json({details:{...otherDetails},role});
    */
    }
}


const confirmAccount =(req,res)=>{
    User.updateOne({_id:String(req.params.id)},{$set:{confirm:1}})
        .then(result=>res.status(200).json({msg:'account has been confirmed!'}))
        .catch(err=>res.status(500).json({msg:'something went wrong!'}))
}

const generateAccessToken = async (req,res)=>{

    //returns error if token isn't provided
    if(!req.body.refresh){
        return res.status(401).json({msg:'Token not found'})
    }

    //returns error if token doesn't exist
    if(!refreshTokens.includes(req.body.refresh)){
        console.log("req body",req.body.refresh)
        console.log("tab",refreshTokens)
        return res.status(403).json({
            errors:[
                {
                    msg:"invalid refresh token"
                }
            ]
        })
    }
    try{
        const user = jwt.verify(
            req.body.refresh,
            process.env.REFRESH_TOKEN_SECRET
        );
        const {email} = user;
        const accessToken = jwt.sign({email},process.env.ACCESS_TOKEN_SECRET,{expiresIn: "5s"});
        return res.json(accessToken);
    }
    catch(error){
        return res.status(403).json({
            errors:[
                {
                    msg:"invalid token"
                }
            ]
        })
    }
}

const logout=(req,res)=>{
    console.log("refresh",refreshTokens);
    refreshTokens=refreshTokens.filter((token)=>token !==req.body);
    return res.status(204).json({msg:"logout successful"})
}

module.exports={registerUser,confirmAccount,login,logout,refreshTokens,generateAccessToken}