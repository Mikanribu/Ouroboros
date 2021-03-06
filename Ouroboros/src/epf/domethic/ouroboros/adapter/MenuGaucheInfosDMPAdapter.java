package epf.domethic.ouroboros.adapter;

import java.util.ArrayList;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.data.DetailListe;
import epf.domethic.ouroboros.data.TitreListe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
public class MenuGaucheInfosDMPAdapter extends BaseExpandableListAdapter {
 
	private Context context;
	private ArrayList<TitreListe> deptList;
  
	public MenuGaucheInfosDMPAdapter(Context context, ArrayList<TitreListe> deptList) {
		this.context = context;
		this.deptList = deptList;
	}
  
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<DetailListe> productList = deptList.get(groupPosition).getProductList();
		return productList.get(childPosition);
	}
 
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
 
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
   
		DetailListe detailInfo = (DetailListe) getChild(groupPosition, childPosition);
		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.adapter_menu_infos_dmp, null);
		}
   
		TextView sequence = (TextView) view.findViewById(R.id.tvSequence);
		sequence.setText(detailInfo.getSequence().trim() + ") ");
		TextView childItem = (TextView) view.findViewById(R.id.tvChildItem);
		childItem.setText(detailInfo.getName().trim());
   
		return view;
	}
 
	@Override
	public int getChildrenCount(int groupPosition) {
		
		ArrayList<DetailListe> productList = deptList.get(groupPosition).getProductList();
		return productList.size();
 
	}
 
	@Override
	public Object getGroup(int groupPosition) {
		return deptList.get(groupPosition);
	}
 
	@Override
	public int getGroupCount() {
		return deptList.size();
	}
	 
	 @Override
	 public long getGroupId(int groupPosition) {
	  return groupPosition;
	 }
	 
	 @Override
	 public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {
	   
		 TitreListe headerInfo = (TitreListe) getGroup(groupPosition);
		 if (view == null) {
			 LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 view = inf.inflate(R.layout.menu_infos_dmp_titre, null);
		 }	   
		  TextView heading = (TextView) view.findViewById(R.id.heading);
		  heading.setText(headerInfo.getName().trim());
		  return view;
	 }
	 
	 @Override
	 public boolean hasStableIds() {
	  return true;
	 }
	 
	 @Override
	 public boolean isChildSelectable(int groupPosition, int childPosition) {
	  return true;
	 }
	   
}
