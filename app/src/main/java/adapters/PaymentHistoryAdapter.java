package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lillian.lillysbeauty.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import model.Payments;

/**
 * Created by RONIN on 8/22/2017.
 */

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.CustomViewHolder> {

    String description;
    String serviceCost;
    String serviceName;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private List<Payments> paymentsList;
    private Context _context;

    public PaymentHistoryAdapter(Context ctx, List<Payments> offeredServicesList) {
        this._context = ctx;
        this.paymentsList = offeredServicesList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_history_list_content, null);
        PaymentHistoryAdapter.CustomViewHolder viewHolder = new PaymentHistoryAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final String payment_id = paymentsList.get(position).PAYMENT_ID;
        final String mpesa_ref = paymentsList.get(position).MPESA_REF;
        final String account_ref = paymentsList.get(position).ACCOUNT_REF;
        final String status = paymentsList.get(position).STATUS;
        final String comment = paymentsList.get(position).COMMENTS;
        final String amount_paid = paymentsList.get(position).BOOKING_AMOUNT;
        final String balance = paymentsList.get(position).BALANCE;
        final String date_paid = paymentsList.get(position).DATE_PAID;
        final String total_cost = paymentsList.get(position).FINAL_AMOUNT;

        holder.txtMpesaRef.setText(mpesa_ref);
        holder.txtAccountRef.setText(account_ref);
        holder.txtStatus.setText(status);
        holder.txtComments.setText(comment);
        holder.txtAmountPaid.setText(amount_paid);
        holder.txtBalance.setText(balance);
        holder.txtDatePaid.setText(date_paid);
        holder.txtTotalCost.setText(total_cost);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*try {
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
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentsList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtAccountRef;
        public final TextView txtMpesaRef;
        public final TextView txtComments, txtStatus;
        public final TextView txtAmountPaid, txtDatePaid, txtTotalCost, txtBalance;

        public CustomViewHolder(View view) {
            super(view);
            mView = view;
            txtAccountRef = (TextView) view.findViewById(R.id.txtAccountRef);
            txtMpesaRef = (TextView) view.findViewById(R.id.txtPaymentRef);
            txtComments = (TextView) view.findViewById(R.id.txtComments);
            txtStatus = (TextView) view.findViewById(R.id.txtStatus);
            txtAmountPaid = (TextView) view.findViewById(R.id.txtBookingAmount);
            txtDatePaid = (TextView) view.findViewById(R.id.txtDatePaid);
            txtTotalCost = (TextView) view.findViewById(R.id.txtTotalCost);
            txtBalance = (TextView) view.findViewById(R.id.txtBalance);
        }
    }
}
