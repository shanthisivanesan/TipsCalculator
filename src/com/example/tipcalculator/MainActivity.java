package com.example.tipcalculator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	SeekBar seek;
	TextView tipAmount;
	EditText intialAmount ;
    TextView finalAmount;
    TextView tipPercent;
    EditText etSplit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intialAmount = (EditText)findViewById(R.id.etAmount);
		seek=(SeekBar) findViewById(R.id.sbPercent);
		
		etSplit =(EditText)findViewById(R.id.etSplit);
		//Get handle to Controls
		tipAmount = (TextView)findViewById(R.id.tvTipAmount);
        finalAmount = (TextView)findViewById(R.id.tvFinalAmount);
        tipPercent =(TextView)findViewById(R.id.tvPercent);
		        
		//Initial Amount Change
		intialAmount.addTextChangedListener(new TextWatcher(){
		    public void afterTextChanged(Editable s) {
		    	
		    	CalculateTip();
		    	//Toast.makeText(getApplicationContext(), String.valueOf(progress),Toast.LENGTH_LONG).show();
		    }
		    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
		    public void onTextChanged(CharSequence s, int start, int before, int count){}
		}); 
		
		//Number of person split
		etSplit.addTextChangedListener(new TextWatcher(){
		    public void afterTextChanged(Editable s) {
		    	if(etSplit != null || !etSplit.getText().equals(""))
		    	CalculateTip();
		    }
		    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
		    public void onTextChanged(CharSequence s, int start, int before, int count){}
		}); 
		
	    //Percent change
	     seek.setProgress(0); 
	     seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() { 	        
			@Override 
	         public void onStopTrackingTouch(SeekBar seekBar) { 
	             
				// TODO Auto-generated method stub 
	            // Toast.makeText(getBaseContext(), "discrete = "+String.valueOf(discrete), Toast.LENGTH_SHORT).show(); 
	         } 

	         @Override 
	         public void onStartTrackingTouch(SeekBar seekBar) { 
	             // TODO Auto-generated method stub 

	         } 

	         @Override 
	         public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {  
	        	 //Log.i("Selected Item in percent", Integer.toString(progress)); 
	        	 CalculateTip();
	         } 
	     }); 
	}
	
	/*
	 * CalculateTip: calculate Tip and display the results
	 */
	public void CalculateTip()
	{
		try
		{
			int split = Integer.parseInt(etSplit.getText().toString());
			if(split<=0)	split=1;
	        //Get Values    
	        float amt = Float.parseFloat(intialAmount.getText().toString());
	        int progress = seek.getProgress();
	        
	        //Calculate tip
			 double tip = amt * (progress/100.0);
			 double splitamt = (amt + tip)/split;
			 
			//Set the controls with values
			 tipPercent.setText(progress+"%");
	        
	        //display only 2 digit after decimal
	        tipAmount.setText(String.format("%.2f", tip));
	        finalAmount.setText(String.format("%.2f", splitamt));
		}
		catch(NumberFormatException e)
	   {
			Log.i("Selected Item in percent",String.valueOf(etSplit.getText())); 
			
	   }
        
	}
}
