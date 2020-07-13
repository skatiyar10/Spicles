package com.project.Spicles.options;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.Spicles.MyBroadcast;
import com.project.Spicles.OrderPlacedActivity;
import com.project.Spicles.PaymentActivity;
import com.project.Spicles.R;
import com.project.Spicles.product.ItemDetailsActivity;
import com.project.Spicles.startup.MainActivity;
import com.project.Spicles.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import static com.project.Spicles.fragments.ImageFragment.STRING_IMAGE_POSITION;
import static com.project.Spicles.fragments.ImageFragment.STRING_IMAGE_URI;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {
    static int sum=0;
    private static final String TAG = PaymentActivity.class.getSimpleName();
    private static Context mContext;
    MyBroadcast broadcast;
    TextView pay;
    static TextView amount;
    private static int totalPrice=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        Checkout.preload(getApplicationContext());
        mContext = CartActivity.this;
        amount = findViewById(R.id.text_action_bottom1);
        pay = findViewById(R.id.text_action_bottom2);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
        ArrayList<String> cartlistImageUri =imageUrlUtils.getCartListImageUri();
        ArrayList<String> name =imageUrlUtils.getCName();
        ArrayList<String> desc =imageUrlUtils.getCDesc();
        ArrayList<String> price =imageUrlUtils.getCPrice();
        //Show cart layout based on items
        setCartLayout();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerView.setAdapter(new CartActivity.SimpleStringRecyclerViewAdapter(recyclerView, cartlistImageUri,name,desc,price));
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Succesful " , Toast.LENGTH_SHORT).show();
            //Bitmap bitmap =  getRecyclerViewScreenshot(CartActivity.SimpleStringRecyclerViewAdapter.ViewHolder);
            MainActivity.notificationCountCart=0;
            setCartLayout();
            Intent i = new Intent(getApplicationContext(), OrderPlacedActivity.class);
            i.putExtra("order_id",razorpayPaymentID);
            startActivity(i);

        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<CartActivity.SimpleStringRecyclerViewAdapter.ViewHolder> {

        private ArrayList<String> mCartlistImageUri;
        private  ArrayList<String> cName;
        private  ArrayList<String> cDesc;
        private  ArrayList<String> cPrice;
        private RecyclerView mRecyclerView;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final TextView mItemName,mItemDes,mItemPrice;
            public final LinearLayout mLayoutItem, mLayoutRemove , mLayoutEdit;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (SimpleDraweeView) view.findViewById(R.id.image_cartlist);
                mItemName = view.findViewById(R.id.item_name);
                mItemDes = view.findViewById(R.id.item_desc);
                mItemPrice=view.findViewById(R.id.item_price);
                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item_desc);
                mLayoutRemove = (LinearLayout) view.findViewById(R.id.layout_action1);
                mLayoutEdit = (LinearLayout) view.findViewById(R.id.layout_action2);
               // mLayoutPayment = view.findViewById(R.id.layout_payment);
            }
        }

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, ArrayList<String> wishlistImageUri,ArrayList<String> name, ArrayList<String> desc, ArrayList<String> price) {
            mCartlistImageUri = wishlistImageUri;
            cName=name;
            cDesc=desc;
            cPrice=price;
            mRecyclerView = recyclerView;
        }

        @Override
        public CartActivity.SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cartlist_item, parent, false);
            return new CartActivity.SimpleStringRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onViewRecycled(CartActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView.getController().onDetach();
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView.getTopLevelDrawable().setCallback(null);
//                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        @Override
        public void onBindViewHolder(final CartActivity.SimpleStringRecyclerViewAdapter.ViewHolder holder, final int position) {

            final Uri uri = Uri.parse(mCartlistImageUri.get(position));
            holder.mImageView.setImageURI(uri);
            holder.mItemName.setText(cName.get(position));
            holder.mItemDes.setText(cDesc.get(position));
            holder.mItemPrice.setText(cPrice.get(position));
            int a = Integer.parseInt(holder.mItemPrice.getText().toString());
            totalPrice = totalPrice + a;
            amount.setText("Rs." + totalPrice);

            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                    intent.putExtra(STRING_IMAGE_URI,mCartlistImageUri.get(position));
                    intent.putExtra(STRING_IMAGE_POSITION, position);
                    mContext.startActivity(intent);
                }
            });
            //Toast.makeText(mContext,sum,Toast.LENGTH_SHORT).show();
            Log.e("amount",sum+"");
           //Set click action
            holder.mLayoutRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                    imageUrlUtils.removeCartListImageUri(position);
                    notifyDataSetChanged();
                    //Decrease notification count
                    MainActivity.notificationCountCart--;

                }
            });

            //Set click action
            holder.mLayoutEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });


        }

        @Override
        public int getItemCount() {
            return mCartlistImageUri.size();
        }
    }

    protected void setCartLayout(){
        LinearLayout layoutCartItems = (LinearLayout) findViewById(R.id.layout_items);
        LinearLayout layoutCartPayments = (LinearLayout) findViewById(R.id.layout_payment);
        LinearLayout layoutCartNoItems = (LinearLayout) findViewById(R.id.layout_cart_empty);

        if(MainActivity.notificationCountCart >0){
            layoutCartNoItems.setVisibility(View.GONE);
            layoutCartItems.setVisibility(View.VISIBLE);
            layoutCartPayments.setVisibility(View.VISIBLE);
        }else {
            layoutCartNoItems.setVisibility(View.VISIBLE);
            layoutCartItems.setVisibility(View.GONE);
            layoutCartPayments.setVisibility(View.GONE);

            Button bStartShopping = (Button) findViewById(R.id.bAddNew);
            bStartShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Spicles");
            options.put("description", "Powered by Razorpay");
            //You can omit the image option to fetch the image from dashboard
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalPrice+"00");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9876543210");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    public static Bitmap getRecyclerViewScreenshot(RecyclerView view) {
        int size = view.getAdapter().getItemCount();
        RecyclerView.ViewHolder holder = view.getAdapter().createViewHolder(view, 0);
        view.getAdapter().onBindViewHolder(holder, 0);
        holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
        Bitmap bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), holder.itemView.getMeasuredHeight() * size,
                Bitmap.Config.ARGB_8888);
        Canvas bigCanvas = new Canvas(bigBitmap);
        bigCanvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        int iHeight = 0;
        holder.itemView.setDrawingCacheEnabled(true);
        holder.itemView.buildDrawingCache();
        bigCanvas.drawBitmap(holder.itemView.getDrawingCache(), 0f, iHeight, paint);
        holder.itemView.setDrawingCacheEnabled(false);
        holder.itemView.destroyDrawingCache();
        iHeight += holder.itemView.getMeasuredHeight();
        for (int i = 1; i < size; i++) {
            view.getAdapter().onBindViewHolder(holder, i);
            holder.itemView.setDrawingCacheEnabled(true);
            holder.itemView.buildDrawingCache();
            bigCanvas.drawBitmap(holder.itemView.getDrawingCache(), 0f, iHeight, paint);
            iHeight += holder.itemView.getMeasuredHeight();
            holder.itemView.setDrawingCacheEnabled(false);
            holder.itemView.destroyDrawingCache();
        }
        return bigBitmap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        //unregisterReceiver(broadCast);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }
}
