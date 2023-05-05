package com.example.pr5.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Fragment4 extends Fragment {

    EditText inputText;
    TextView response;
    public static final String APP_PREFERENCES_NAME = "name";
    Button saveButton;
    Button readButton;

    private String filename = "text.txt";
    private String filepath = "MyFileStorage";
    String myData = "";

    public Fragment4() {
        super(R.layout.fragment4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, null);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        inputText = (EditText) getActivity().findViewById(R.id.myInputText);
        response = (TextView) getActivity().findViewById(R.id.response);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        saveButton = (Button) getActivity().findViewById(R.id.saveExternalStorage);
        File myExternalFile = new File("text.text");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(APP_PREFERENCES_NAME, inputText.getText().toString());
                    editor.apply();

                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(inputText.getText().toString().getBytes());
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputText.setText("");
                response.setText("Отправка файла...");
            }
        });

        readButton = (Button) getActivity().findViewById(R.id.getExternalStorage);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if(sharedPref.contains(APP_PREFERENCES_NAME)) {
                        inputText.setText(sharedPref.getString(APP_PREFERENCES_NAME, ""));
                        response.setText("Получение файла...");
                    }
                    else{
                        response.setText("Не найдено");
                    }

                    FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;


                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                    }

                    in.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputText.setText(myData);
            }
        });

    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public void onResume() {

        super.onResume();

        Button button = (Button) getView().findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.fragment1);
            }
        });
    }
}