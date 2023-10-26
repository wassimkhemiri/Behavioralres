const mongoose = require ('mongoose');

const userSchema = mongoose.Schema({
    firstName:{
        type : String,
        required : true
    },

    lastName:{
        type:String,
        required:true
    },

    userName:{
        type:String,
        required:true
    },

    email:{
        type:String,
        required:true
    },

    password:{
        type:String
    },

    birthDate: {
        type: Date,
    },

    state: {
         type: String,
    },

    img: {
         type: String
    },

    phone:{
        type:Number
    },

    gender:{
        type:String,
        enum:['male','female']
    },

    confirm: {
        type: Number,
        default: 0
    },

    role:{
        type:String,
        enum:['USER','ADMIN'],
        default:'ADMIN'
    },

    activeUser:{
        type:Number,
        default:1
    },

    PerformedOperations:[
        {
        type:mongoose.Schema.Types.ObjectId,
        ref:'Operation'
    }
    ]


},
    {
        timestamps:true
    })

module.exports = User = mongoose.model('User',userSchema);