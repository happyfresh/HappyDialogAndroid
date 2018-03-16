package com.happyfresh.happydialogandroid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.happyfresh.happydialog.HappyDialogBuilder;
import com.happyfresh.happydialog.HappyDialogButton;
import com.happyfresh.happydialog.HappyDialogInterface;

import org.jetbrains.annotations.NotNull;

/**
 * Created by adefruandta on 2/25/18.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HappyDialogBuilder builder = new HappyDialogBuilder(v.getContext());
                builder.setFeatureImage(R.drawable.ic_launcher_background);
                builder.setTitle("ABC");
                builder.setMessage("DEF");
                builder.setCustomView(R.layout.custom_view);
                builder.addButton(ButtonType.PRIMARY, "Test", new HappyDialogButton.OnClickListener() {
                    @Override
                    public void onClick(@NotNull View view, @NotNull HappyDialogButton button,
                            @NotNull HappyDialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "Hallo", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                builder.addOnCancelListener(new HappyDialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(@NotNull HappyDialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_LONG).show();
                    }
                });
                builder.addOnDismissListener(new HappyDialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(@NotNull HappyDialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "onDismiss", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
    }

    enum ButtonType implements HappyDialogButton.Type {
        PRIMARY(0, R.color.colorPrimary, android.R.color.white);

        int id;

        int background;

        int textColor;

        ButtonType(int id, int background, int textColor) {
            this.id = id;
            this.background = background;
            this.textColor = textColor;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @NotNull
        @Override
        public Drawable getBackground() {
            return ContextCompat.getDrawable(App.sInstance, this.background);
        }

        @Override
        public int getTextColor() {
            return ContextCompat.getColor(App.sInstance, this.textColor);
        }
    }
}
