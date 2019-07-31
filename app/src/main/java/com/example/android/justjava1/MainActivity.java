package com.example.android.justjava1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    /**
     * This method is called when the order button is clicked.
     */
     int quantity=2;
     int tprice;


    public void increment(View view)
    {
// increases the quantity by 1
            quantity++;
            display(quantity);
    }

    public void decrement(View view)
    {
// decreases the quantity by 2 until quantity is greater than 0
            if(quantity>0)
             quantity--;
            display(quantity);
    }
    public void submitOrder(View view)
    {
        // finally printing each and every value
        EditText text=findViewById(R.id.edit_text);
        String name = text.getText().toString();
        tprice = calculatePrice();
        String priceMessage = createOrderSummary(tprice,name);
        TextView textview= findViewById(R.id.order_summary_text_view);
        textview.setMovementMethod(new ScrollingMovementMethod());
        display(quantity);
        displayMessage(priceMessage);

    }

    public void send_email(View view)
    {
        EditText text=findViewById(R.id.edit_text);
        String name = text.getText().toString();
        tprice = calculatePrice();
        String priceMessage = createOrderSummary(tprice,name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "COFFEE ORDER FOR"+ " "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }

    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message)
    {
            TextView priceTextView = findViewById(R.id.order_summary_text_view);
            priceTextView.setText(message);
    }
    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice( )
    {
        int baseprice=5;
        CheckBox chocolate= this.findViewById(R.id.chocolate);
        boolean haschocolate = chocolate.isChecked();
        CheckBox whippedcream= this.findViewById(R.id.whippedcream);
        boolean haswhippedcream = whippedcream.isChecked();
        if(haschocolate)
        {
            baseprice+=2;
        }
        if(haswhippedcream)
        {
            baseprice+=3;
        }
            return quantity * baseprice;
    }
    // Prints a proper message
    private String createOrderSummary(int priceOfOrder,String customer)
    {
        return " Name ="+customer+"\n Quantity =" + quantity +" \nTotal = $" +priceOfOrder+ "\n Thank You!";
    }

    private void display(int number)
    {
            TextView quantityTextView = findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + number);
    }
}
