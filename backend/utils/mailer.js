const nodemailer = require("nodemailer");

// async..await is not allowed in global scope, must use a wrapper

// NOTICE :
// mode : if true => email for registration
// mode : if false => email for verification identiy for changing the forgetten password

module.exports = async function main(receiver, mode = true, code) {
    // Generate test SMTP service account from ethereal.email
    // Only needed if you don't have a real mail account for testing
    let testAccount = await nodemailer.createTestAccount();

    // create reusable transporter object using the default SMTP transport
    let transporter = nodemailer.createTransport({
        host: "smtp.gmail.com",
        port: 587,
        secure: false, // true for 465, false for other ports
        auth: {
            user: process.env.GMAIL_USER, // generated ethereal user
            pass: process.env.GMAIL_PASSWORD, // generated ethereal password
        },
    });

    let info;

    if (mode) {
        info = await transporter.sendMail({
            from: '"user activation" <noreply@example.com>',
            to: receiver.email, // list of receivers
            subject: "Hello ", // Subject line
            text: "thank you for registration", // plain text body
            html: `thank you for registration ${receiver.userName}<br/><a href='http://localhost:5000/confirmAccount/${receiver._id}'>confirm</a>`, // html body
        });

    } else {
        info = await transporter.sendMail({
            from: '"Poste" <noreply@example.com>',
            to: receiver.email, // list of receivers
            subject: "verifying identity", // Subject line
            html: `<p>if you want to change your forgetten password you must to verifie your identity using this code below<br /><strong>${code}</strong></p><br /> This code is available for 20min`, // html body
        });
    }
    // send mail with defined transport object

    console.log("Message sent: %s", info.messageId);
    // Message sent: <b658f8ca-6296-ccf4-8306-87d57a0b4321@example.com>

    // Preview only available when sending through an Ethereal account
    console.log("Preview URL: %s", nodemailer.getTestMessageUrl(info));
    // Preview URL: https://ethereal.email/message/WaQKMgKddxQDoou...
}




