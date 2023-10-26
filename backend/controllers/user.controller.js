const User = require('../models/user.model');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const asyncHandler = require('express-async-handler');
const validationRegister=require('../utils/validateRegister');


const {registerUser,confirmAccount,login,logout,generateAccessToken} = require('./Auth');

const getUserById = (req,res)=>{
    if(!req.params.idUser)
        return res.status(400).json('user id required');
    User.findById(req.params.idUser,(err,result)=>{
        if (err)
            return res.status(400).json(err);
        return res.status(200).json(result);
    })
}

const updateUser = async (req,res,next)=>{
    try {
        const updatedUser = await User.findByIdAndUpdate(
            req.user.id,
            { $set: req.body },
            { new: true }
        );
        res.status(200).json(updatedUser);
    } catch (err) {
        next(err);
    }
}


const listUsers = async(req,res)=>{
    const list = await User.find();

    if(!list)
        return res.status(500).json('oops! something went wrong.');
    return res.status(200).json(list);
}

const deleteUser = async(req,res)=>{

    const result = await User.deleteOne({_id:req.user.id});
    if (result)
        return res.status(200).json(result);
    return res.status(500).json('oops! something went wrong.');
}

module.exports={getUserById,registerUser,listUsers,deleteUser,confirmAccount,login,updateUser,logout,generateAccessToken}

