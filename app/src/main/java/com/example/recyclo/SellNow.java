package com.example.recyclo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



import java.util.jar.Attributes;

public class SellNow extends AppCompatActivity {

    private EditText name1;
    private EditText address1;
    private EditText phone1;
    private EditText type1;
    private EditText weight1;
    private TextView price1;
    private Button sell1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_now);
        name1=findViewById(R.id.name);
        address1=findViewById(R.id.address);
        phone1=findViewById(R.id.phone);
        type1=findViewById(R.id.type);
        weight1=findViewById(R.id.weight);
//        price1=findViewById(R.id.price);
        sell1=findViewById(R.id.sell);

        sell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username="divyanshsinghds2002@gmail.com";
                final String password="khqiletylfonfadh";
                String msgToSend1=name1.getText().toString();
                String msgToSend2=address1.getText().toString();
                String msgToSend3=phone1.getText().toString();
                String msgToSend4=type1.getText().toString();
                String msgToSend5=weight1.getText().toString();
//                String msgToSend6=price1.getText().toString();

                Properties props=new Properties();
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port","587");
                Session session=Session.getInstance(props,
                        new javax.mail.Authenticator(){
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username,password);
                            }
                        });
                try{
                    Message message=new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("divyanshsinghds2611@gmail.com"));
                    message.setSubject("Mail for recyclable waste collection");
                    message.setText(msgToSend1+"\n"+msgToSend2+"\n"+msgToSend3+"\n"+msgToSend4+"\n"+msgToSend5);
//                    message.setText(msgToSend2);
//                    message.setText(msgToSend3);
//                    message.setText(msgToSend4);
//                    message.setText(msgToSend5);
//                    message.setText(msgToSend6);
                    Transport.send(message);
                    Toast.makeText(getApplicationContext(),"email sent successfully",Toast.LENGTH_LONG).show();

                    setContentView(R.layout.activity_main);
                    sell1=findViewById(R.id.sell);
                    sell1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(SellNow.this,thankyoupage.class);
                            startActivity(intent);
                        }
                    });
                }
                catch(MessagingException e){
                    throw new RuntimeException(e);
                }

            }
        });
        //to make android fast and smoother
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}