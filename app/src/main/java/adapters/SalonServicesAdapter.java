package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lillian.lillysbeauty.R;
import com.lillian.lillysbeauty.ReservationActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import model.OfferedServices;

/**
 * Created by RONIN on 7/21/2017.
 */

public class SalonServicesAdapter extends RecyclerView.Adapter<SalonServicesAdapter.CustomViewHolder> {

    String description;
    String serviceCost;
    String serviceName;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private List<OfferedServices> _Offered_servicesList;
    private Context _context;

    public SalonServicesAdapter(Context ctx, List<OfferedServices> offeredServicesList) {
        this._context = ctx;
        this._Offered_servicesList = offeredServicesList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salon_service_list_content, null);
        SalonServicesAdapter.CustomViewHolder viewHolder = new SalonServicesAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final String offered_service_id = _Offered_servicesList.get(position).OFFERED_SERVICE_ID;
        final String reservation_id = _Offered_servicesList.get(position).RESERVATION_ID;
        final String service_name = _Offered_servicesList.get(position).SERVICE_NAME;
        final String salon_name = _Offered_servicesList.get(position).SALON_NAME;
        final String service_cost = _Offered_servicesList.get(position).SERVICE_COST;
        final String description = _Offered_servicesList.get(position).SERVICE_DESCRIPTION;

        holder.txtServiceCost.setText(service_cost);
        holder.txtServiceName.setText(service_name);
        holder.txtDescription.setText(description);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context context = v.getContext();
                    jsonObject = new JSONObject();
                    jsonArray = new JSONArray();

                    jsonObject.put(OfferedServices.OFFERED_SERVICE_ID_ARG, offered_service_id);
                    jsonArray.put(jsonObject);

                    Intent intent = new Intent(context, ReservationActivity.class);
                    intent.putExtra(OfferedServices.SELECTED_SERVICES_ARG, jsonArray.toString());
                    intent.putExtra(OfferedServices.SALON_NAME_ARG, salon_name);
                    intent.putExtra(OfferedServices.SERVICE_NAME_ARG, service_name);
                    intent.putExtra(OfferedServices.RESERVED_SERVICE_ID_ARG, reservation_id);
                    intent.putExtra(OfferedServices.SERVICE_COST_ARG, service_cost);
                    intent.putExtra(OfferedServices.SERVICE_DESCRIPTION_ARG, description);
                    intent.putExtra(OfferedServices.RESERVED_SERVICE_ID_ARG, reservation_id);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _Offered_servicesList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtServiceName;
        public final TextView txtServiceCost;
        public final TextView txtDescription;

        public CustomViewHolder(View view) {
            super(view);
            mView = view;
            txtServiceName = (TextView) view.findViewById(R.id.txtServiceName);
            txtServiceCost = (TextView) view.findViewById(R.id.txtServiceCost);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        }
    }
}
