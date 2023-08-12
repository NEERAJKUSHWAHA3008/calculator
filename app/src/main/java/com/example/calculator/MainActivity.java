package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mainValue , solutionValue;
    MaterialButton allClearButton , percentageButton, bracketButton , backButton;
    MaterialButton zeroButton,sevenButton , eightButton , nineButton, fourButton ,fiveButton , sixButton , oneButton , twoButton , threeButton;
    MaterialButton dotButton, equalButton , divideButton , addButton , minusButton , multiplyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainValue=findViewById(R.id.mainValue);
        solutionValue=findViewById(R.id.solutionValue);

        giveId(allClearButton,R.id.allClearButton);
        giveId(percentageButton,R.id.percentButton);
        giveId(bracketButton,R.id.bracketButton);
        giveId(backButton,R.id.backButton);
        giveId(zeroButton,R.id.zeroButton);
        giveId(sevenButton,R.id.sevenButton);
        giveId(eightButton,R.id.eightButton);
        giveId(nineButton,R.id.nineButton);
        giveId(fourButton,R.id.fourButton);
        giveId(fiveButton,R.id.fiveButton);
        giveId(sixButton,R.id.sixButton);
        giveId(oneButton,R.id.oneButton);
        giveId(twoButton,R.id.twoButton);
        giveId(dotButton,R.id.dotButton);
        giveId(equalButton,R.id.equalButton);
        giveId(divideButton,R.id.divideButton);
        giveId(addButton,R.id.addButton);
        giveId(minusButton,R.id.minusButton);
        giveId(multiplyButton,R.id.multiplyButton);
        giveId(threeButton,R.id.threeButton);




    }
    public void giveId(MaterialButton button, int id){
        button=findViewById(id);
        button.setOnClickListener(this);
    }

    public void onClick(View view){
        MaterialButton btn =(MaterialButton) view;
        String btnText=btn.getText().toString();
        String dataText=solutionValue.getText().toString();
        if(btnText.equals("AC")){
            solutionValue.setText("");
            mainValue.setText("0");
            return;
        }
        if(btnText.equals("=")){
            solutionValue.setText(mainValue.getText());
            return;
        }
        if(btnText.equals("C")){
            dataText=dataText.substring(0,dataText.length()-1);
        }else{
            dataText=dataText+btnText;
        }

        solutionValue.setText(dataText);
        String LastResult=getResult(dataText);
        if(!LastResult.equals("err")){
            mainValue.setText(LastResult);
        }
    }

    String getResult(String data){
        try{
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String LastResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(LastResult.endsWith(".0")){
                LastResult=LastResult.replace(".0"," ");
            }
            return LastResult;
        }catch (Exception e){
            return "err";
        }
    }
}