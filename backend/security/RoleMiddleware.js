const ROLES={
    "USER":"USER",
    "ADMIN":"ADMIN"
}

const inRole=(...roles)=>(req,res,next)=>{
    const role=roles.find(role=>req.user.role == role)
    if(!role){
        return res.status(403).json({msg:"access denied : you do not have the authority to access this content"})

    }
    next()
}

module.exports={inRole,ROLES}