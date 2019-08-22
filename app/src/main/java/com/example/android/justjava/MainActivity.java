package com.example.android.justjava;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import static android.R.id.edit;
import static android.R.id.message;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;
    int price;
    int myNumber = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void increaseCoffees(View view)
    {
        myNumber = myNumber + 1;
        displayQuantity(myNumber);
    }

    public void decreaseCoffees(View view)
    {
        if(myNumber > 0) {
            myNumber = myNumber - 1;
        }else{
            myNumber = 0;
            Toast mytoast = Toast.makeText(getApplicationContext(),"It is zero", Toast.LENGTH_LONG);
            mytoast.show();
        }
        displayQuantity(myNumber);
    }

    public void orderCoffees(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:solomonndi96@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary of "+myName());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(myNumber, myCheck(), myCheck2()));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Toast mytoast = Toast.makeText(getApplicationContext(),"Sorry there is currently no application to open this!", Toast.LENGTH_LONG);
            mytoast.show();
        }
        //calculatePrice(myNumber, 10);
        displayMessage(createOrderSummary(myNumber, myCheck(), myCheck2()));
        EditText editText = (EditText) findViewById(R.id.myName);
        editText.setText("");
    }

    private String createOrderSummary(int price, boolean hasCheck, boolean hasCheck2)
    {
        int myPrice = cal(price);
        String priceMessage = getString(R.string.person_name, myName()) +"\n";
        priceMessage+="Add Whipped Cream? " + hasCheck +"\n";
        priceMessage+="Add Chocolate Toppings? "+ hasCheck2 +"\n";
        priceMessage = priceMessage + "Quantity: " + myNumber + "\n";
        priceMessage = priceMessage + "Total: $" + myPrice + "\n Thank you!";
        priceMessage = priceMessage +"\n" + getString(R.string.have_a_nice_day);
        return priceMessage;
    }
    private void calculatePrice(int quantity, int pricePerCup)
    {
        displayQuantity(quantity);
        int price = quantity * pricePerCup;
    }

    private int cal(int quantity)
    {
        price = quantity * 5;

        if(myCheck() && myCheck2()){
            price = quantity * (5 + 2 + 1);
        }else if(myCheck()){
            price = quantity * (5 + 1);
        }else if(myCheck2()){
            price = quantity * (5 + 2);
        }
        return price;
    }
    private void displayQuantity(int num)
    {
        textView = (TextView) findViewById(R.id.quantity_text_view);
        textView.setText("" + num);
    }

    private void displayMessage(String message)
    {
        textView2 = (TextView) findViewById(R.id.order_summary_text_view);
        textView2.setText(message);
    }
    private String myName(){
        EditText editText = (EditText) findViewById(R.id.myName);
        String name = editText.getText().toString();
        return name;
    }
    private boolean myCheck(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.check);
        if(checkBox.isChecked()){
            return true;
        }else{
            return false;
        }
    }
    private boolean myCheck2(){
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkChoco);
        if(checkBox1.isChecked()){
            return true;
        }else{
            return false;
        }
    }
}
