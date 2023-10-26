const mongoose = require('mongoose');
const dotenv = require ('dotenv').config();

const dataBase = process.env.MONGO;

const connectDB = async ()=>{
    try{
        await mongoose.connect(
            dataBase,
            {
                useNewUrlParser: true
            }
        )
        console.log('MongoDB is Connected...');
    }

     catch (err) {
        console.error(err.message);
        process.exit(1);
    }
}

module.exports = connectDB;