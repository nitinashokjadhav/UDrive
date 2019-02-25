package com.mapview;

import java.lang.reflect.Method;
import java.util.List;

import org.w3c.dom.Text;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class BalloonItemizedOverlay <Item> extends ItemizedOverlay<OverlayItem> {
	
    //private MapView mapView;
	private RelativeLayout RLayout;
	private MapView mapView;
	private BalloonOverlayView balloonView;
	private View clickRegion;
	private int viewOffset;
	final MapController mc;
	public BalloonItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(defaultMarker);
		// TODO Auto-generated constructor stub		
		this.mapView = mapView;
		viewOffset = 0;
		mc = mapView.getController();
	}
	
	
	public void setBalloonBottomOffset(int pixels) {
		viewOffset = pixels;
	}
	
	
	protected boolean onBalloonTap(int index) {
		return false;
	}
	
	
	@Override
	protected final boolean onTap(int index) {

		boolean isRecycled;
		final int thisIndex;
		GeoPoint point;

		thisIndex = index;
		point = createItem(index).getPoint();

		if (balloonView == null) {
			balloonView = new BalloonOverlayView(mapView.getContext(), viewOffset);
			clickRegion = (View) balloonView.findViewById(R.id.RelativeLayout01);
			isRecycled = false;
		} else {
			isRecycled = true;
		}

		balloonView.setVisibility(View.GONE);

		List<Overlay> mapOverlays = mapView.getOverlays();
		if (mapOverlays.size() > 1) {
			hideOtherBalloons(mapOverlays);
		}

		balloonView.setData(createItem(index));
		LayoutParams params=new LayoutParams(323, 300);
		//MapView.LayoutParams params=new MapView.LayoutParams(300, 340,point, MapView.LayoutParams.BOTTOM_CENTER);
	    
		//MapView.LayoutParams params = new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, point, MapView.LayoutParams.BOTTOM_CENTER);
		//params.mode = MapView.LayoutParams.MODE_MAP;

		setBalloonTouchListener(thisIndex);

		balloonView.setVisibility(View.VISIBLE);

		if (isRecycled) {
			balloonView.setLayoutParams(params);
		} else {
	   
		mapView.addView(balloonView, params);
		
		}

		//mc.animateTo(point);

		return true;
	}
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void hideOtherBalloons(List<Overlay> overlays) {

		for (Overlay overlay : overlays) {
			if (overlay instanceof BalloonItemizedOverlay<?> && overlay != this) {
				((BalloonItemizedOverlay<?>) overlay).hideBalloon();
			}
		}

	}
	private void setBalloonTouchListener(final int thisIndex) {

		/*try {
			@SuppressWarnings("unused")
			Method m = this.getClass().getDeclaredMethod("onBalloonTap", int.class);

			clickRegion.setOnTouchListener(new OnTouchListener() {
				

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					View l =  ((View) v.getParent()).findViewById(R.id.balloon_main_layout);
					Drawable d = l.getBackground();

					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						int[] states = {android.R.attr.state_pressed};
						if (d.setState(states)) {
							d.invalidateSelf();
						}
						return true;
					} else if (event.getAction() == MotionEvent.ACTION_UP) {
						int newStates[] = {};
						if (d.setState(newStates)) {
							d.invalidateSelf();
						}
						// call overridden method
						onBalloonTap(thisIndex);
						return true;
					} else {
						return false;
					}

					
				}
			});

		} catch (SecurityException e) {
			Log.e("BalloonItemizedOverlay", "setBalloonTouchListener reflection SecurityException");
			return;
		} catch (NoSuchMethodException e) {
			// method not overridden - do nothing
			return;
		}*/

	}
	private void hideBalloon() {
		if (balloonView != null) {
			balloonView.setVisibility(View.GONE);
		}
	}
}
