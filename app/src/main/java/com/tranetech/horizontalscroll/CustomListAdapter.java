package com.tranetech.horizontalscroll;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {
	Context context;
	private ArrayList<String> PRICE, ITEM, IMAGE;
	int layoutId;
	Holder holder;
	public View view;
	public int currPosition = 0;

	public CustomListAdapter(Context context, int textViewResourceId,
							 ArrayList<String> PRICE, ArrayList<String> ITEM, ArrayList<String> IMAGE) {
		super(context, android.R.layout.simple_list_item_1, PRICE);
		this.context = context;
		this.PRICE = PRICE;
		this.ITEM = ITEM;
		this.IMAGE = IMAGE;
		layoutId = textViewResourceId;

	}

	@Override
	public int getCount() {
		return PRICE.size();
	}

	@Override
	public String getItem(int position) {
		return PRICE.get(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		RelativeLayout layout;

		if (convertView == null) {

			layout = (RelativeLayout) View.inflate(context, layoutId, null);

			holder = new Holder();

			holder.title = (ImageView) layout.findViewById(R.id.iv_image);
			holder.tv_price = (TextView) layout.findViewById(R.id.tv_price);
			holder.tv_item = (TextView) layout.findViewById(R.id.tv_item);
			layout.setTag(holder);

		} else {
			layout = (RelativeLayout) convertView;
			view = layout;
			holder = (Holder) layout.getTag();
		}

		String newsSource = getItem(position);

		//holder.title.setImageResource(Integer.parseInt(IMAGE.set(position, newsSource).toString()));

		Picasso.with(getContext().getApplicationContext()).load(IMAGE.set(position, newsSource).toString())
				.placeholder(R.drawable.placeholder)
				.error(R.drawable.warning)
				.fit()
				.into(holder.title);

		holder.tv_price.setText("RS. " + PRICE.set(position, newsSource).toString());
		holder.tv_item.setText(ITEM.set(position, newsSource).toString());
		Log.v("Test", "lo frm newsadpater");

		holder.title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext().getApplicationContext(), "You Clicked " + position, Toast.LENGTH_SHORT).show();
			}
		});
		return layout;

	}

	private class Holder {
		public ImageView title;
		public TextView tv_price, tv_item;

	}

	public int getCurrentPosition() {
		return currPosition;
	}
}
