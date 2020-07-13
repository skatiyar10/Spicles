package com.project.Spicles.product;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.project.Spicles.R;
import com.project.Spicles.fragments.ImageFragment;
import com.project.Spicles.fragments.ViewPagerActivity;
import com.project.Spicles.notification.NotificationCountSetClass;
import com.project.Spicles.options.CartActivity;
import com.project.Spicles.startup.MainActivity;
import com.project.Spicles.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ItemDetailsActivity extends AppCompatActivity {
    int imagePosition;
    String stringImageUri,stringName,stringDes,stringPrice;
    TextView share,wishlist;
    ImageView wish;
    LinearLayout wishl;

    private String[] mValues,mName,mDes,mPrice;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        SimpleDraweeView mImageView = (SimpleDraweeView)findViewById(R.id.image1);
       final TextView mItemname = (TextView)findViewById(R.id.item_name) ;
       TextView mItemprice = (TextView)findViewById(R.id.item_price) ;
       TextView mItemdes = (TextView)findViewById(R.id.item_desc) ;
        TextView textViewAddToCart = (TextView)findViewById(R.id.text_action_bottom1);
        TextView textViewBuyNow = (TextView)findViewById(R.id.text_action_bottom2);
        share = findViewById(R.id.text_action1);
        wishlist=findViewById(R.id.text_action3);
        wish = findViewById(R.id.wish);
        wishl=findViewById(R.id.layout_action3);
       // wish.setOnClickListener();
       /* wishl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                imageUrlUtils.addWishlistImageUri(mValues[position],mName[position],mDes[position],mPrice[position]);
                wish.setImageResource(R.drawable.ic_favorite_black_18dp);
               // notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Item added to wishlist.",Toast.LENGTH_SHORT).show();
            }
        });
        */


        //Getting image uri from previous screen
        if (getIntent() != null) {
            stringImageUri = getIntent().getStringExtra(ImageFragment.STRING_IMAGE_URI);
            imagePosition = getIntent().getIntExtra(ImageFragment.STRING_IMAGE_POSITION,0);
            stringName = getIntent().getStringExtra(ImageFragment.STRING_NAME);
            stringDes = getIntent().getStringExtra(ImageFragment.STRING_DES);
            stringPrice = getIntent().getStringExtra(ImageFragment.STRING_PRICE);
        }
        Uri uri = Uri.parse(stringImageUri);
        mImageView.setImageURI(uri);
        mItemname.setText(stringName);
        mItemdes.setText(stringDes);
        mItemprice.setText(stringPrice);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ItemDetailsActivity.this, ViewPagerActivity.class);
                    intent.putExtra("position", imagePosition);
                    startActivity(intent);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, uriToImage);
                sendIntent.setType("image/jpeg");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                 **/
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                //shareIt();
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                imageUrlUtils.addWishlistImageUri(mValues[1],mName[1],mDes[1],mPrice[1]);
                wish.setImageResource(R.drawable.ic_favorite_black_18dp);
                //notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Item added to wishlist.",Toast.LENGTH_SHORT).show();
            }
        });

        textViewAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                imageUrlUtils.addCartListImageUri(stringImageUri,stringName,stringDes,stringPrice);
                Toast.makeText(ItemDetailsActivity.this,"Item added to cart.",Toast.LENGTH_SHORT).show();
                MainActivity.notificationCountCart++;
                NotificationCountSetClass.setNotifyCount(MainActivity.notificationCountCart);
            }
        });

        textViewBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                imageUrlUtils.addCartListImageUri(stringImageUri,stringName,stringDes,stringPrice);
                MainActivity.notificationCountCart++;
                NotificationCountSetClass.setNotifyCount(MainActivity.notificationCountCart);
                startActivity(new Intent(ItemDetailsActivity.this, CartActivity.class));

            }
        });

    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/scrnshot.png"); ////File imagePath
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Check this out!! Exciting offer";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Catch score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

   /** private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "My highest score with screen shot";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Catch score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }**/
   public void checkPermission(String permission, int requestCode)
   {
       if (ContextCompat.checkSelfPermission(ItemDetailsActivity.this, permission)
               == PackageManager.PERMISSION_DENIED) {

           // Requesting the permission
           ActivityCompat.requestPermissions(ItemDetailsActivity.this,
                   new String[] { permission },
                   requestCode);
       }
       else {
           Toast.makeText(ItemDetailsActivity.this,
                   "Permission already granted",
                   Toast.LENGTH_SHORT)
                   .show();
       }
   }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);


         if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ItemDetailsActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(ItemDetailsActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
