package com.my.sadebuser.act.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.databinding.ActivityProfileBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    public static final int PICK_IMAGE = 1;
    private Bitmap bitmap;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        setProfileData();

        init();

        binding.RRLogout.setOnClickListener(v -> {
            alertDialog = new AlertDialog.Builder(ProfileActivity.this)
                    .setMessage(R.string.are_you_sure_you_want_to_logout)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            SharedPrefsManager.getInstance().clearPrefs();
                            startActivity(new Intent(ProfileActivity.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    }).show();


        });
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.llName.setOnClickListener(v -> {

            startActivity(new Intent(this, UpdateName.class));
        });

        binding.RREmail.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateEmail.class));
        });

        binding.RRGender.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateGender.class));
        });

        binding.RRPhoneNumber.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdatePhoneNumber.class));
        });

        binding.RRchangePassword.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdatePassword.class));
        });
        binding.RRInvite.setOnClickListener(v -> {

            String link = "https://play.google.com/store/apps/";
            Uri uri = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, link.toString());

            startActivity(Intent.createChooser(intent, "Share Link"));

//            startActivity(new Intent(this, InviteFriends.class));
        });

        binding.llMain.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateName.class));
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setProfileData();
    }

    private void setProfileData() {
        ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        if (model!=null){

            Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(binding.ivUser);
            binding.tvStatus.setText(model.getResult().getEmail());
//            binding.tvEmail.setText(model.getResult().getEmail());
            binding.tvName.setText(model.getResult().getUserName());
            binding.tvName.setText(model.getResult().getUserName());
            binding.tvNo.setText(model.getResult().getMobile());
            binding.tvGender.setText(model.getResult().getGender());

        }
      }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                binding.ivUser.setImageBitmap(bitmap);
            }
        }

    }

    public void init() {
//        binding.ivUser.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//
//            }
//        });
    }

}