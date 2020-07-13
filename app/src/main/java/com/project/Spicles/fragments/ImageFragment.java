package com.project.Spicles.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.Spicles.R;
import com.project.Spicles.product.ItemDetailsActivity;
import com.project.Spicles.startup.MainActivity;
import com.project.Spicles.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;


public class ImageFragment extends Fragment {

    public static final String STRING_IMAGE_URI = "ImageUri";
    public static final String STRING_NAME = "Name";
    public static final String STRING_DES = "Des";
    public static final String STRING_PRICE = "Price";
    public static final String STRING_IMAGE_POSITION = "ImagePosition";
    private static MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.layout_recylerview_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {

        String[] items=null;
        String[] item_names = null;
        String[] item_des =null;
        String[] item_price = null;

        if (ImageFragment.this.getArguments().getInt("type") == 1){
            items =ImageUrlUtils.getOffersUrls();
            item_names = ImageUrlUtils.getOffersName();
            item_des = ImageUrlUtils.getOffersDes();
            item_price=ImageUrlUtils.getOffersPrice();
        }else if (ImageFragment.this.getArguments().getInt("type") == 2){
            items =ImageUrlUtils.getSnacksUrls();
            item_names=ImageUrlUtils.getSnacksName();
            item_des = ImageUrlUtils.getSnacksDes();
            item_price=ImageUrlUtils.getSnacksPrice();
        }else if (ImageFragment.this.getArguments().getInt("type") == 3){
            items =ImageUrlUtils.getPicklesUrls();
            item_names=ImageUrlUtils.getPicklesName();
            item_des = ImageUrlUtils.getSnacksDes();
            item_price=ImageUrlUtils.getSnacksPrice();
        }else if (ImageFragment.this.getArguments().getInt("type") == 4){
            items =ImageUrlUtils.getSpicesUrls();
            item_names=ImageUrlUtils.getSpicesName();
            item_des = ImageUrlUtils.getSnacksDes();
            item_price=ImageUrlUtils.getSnacksPrice();
      //  }else if (ImageFragment.this.getArguments().getInt("type") == 5){
         //   items =ImageUrlUtils.getBooksUrls();
        }else {
            items = ImageUrlUtils.getImageUrls();
            item_names = ImageUrlUtils.getImageName();
            item_des=ImageUrlUtils.getImagedes();
            item_price=ImageUrlUtils.getImageprice();
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(recyclerView, items,item_names,item_des,item_price));
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private String[] mValues;
        private String[] mName;
        private  String[] mDes;
        private String[] mPrice;
        private RecyclerView mRecyclerView;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SimpleDraweeView mImageView;
            public final TextView mItemname, mItemprice, mItemdes;
            public final LinearLayout mLayoutItem;
            public final ImageView mImageViewWishlist;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (SimpleDraweeView) view.findViewById(R.id.image1);
                mItemname = (TextView)view.findViewById(R.id.item_name) ;
                mItemprice = (TextView)view.findViewById(R.id.item_price) ;
                mItemdes = (TextView)view.findViewById(R.id.item_desc) ;
                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item);
                mImageViewWishlist = (ImageView) view.findViewById(R.id.ic_wishlist);
            }
        }

        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, String[] items,String[] item_names,String[] item_des,String[] item_price) {
            mValues = items;
            mName = item_names;
            mDes=item_des;
            mPrice=item_price;
            mRecyclerView = recyclerView;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView.getController().onDetach();
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView.getTopLevelDrawable().setCallback(null);
//                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final Uri uri = Uri.parse(mValues[position]);
            holder.mImageView.setImageURI(uri);
            holder.mItemname.setText(mName[position]);
            holder.mItemdes.setText(mDes[position]);
            holder.mItemprice.setText(mPrice[position]);
            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ItemDetailsActivity.class);
                    intent.putExtra(STRING_IMAGE_URI, mValues[position]);
                    intent.putExtra(STRING_NAME,mName[position]);
                    intent.putExtra(STRING_DES,mDes[position]);
                    intent.putExtra(STRING_PRICE,mPrice[position]);
                    intent.putExtra(STRING_IMAGE_POSITION, position);
                    mActivity.startActivity(intent);

                }
            });

            holder.mImageViewWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                    imageUrlUtils.addWishlistImageUri(mValues[position],mName[position],mDes[position],mPrice[position]);
                    holder.mImageViewWishlist.setImageResource(R.drawable.ic_favorite_black_18dp);
                    notifyDataSetChanged();
                    Toast.makeText(mActivity,"Item added to wishlist.",Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public int getItemCount() {
            return mValues.length;
        }
    }
}
