package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mugo.expensemanager.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import model.Expenses;

/**
 * Created by RONIN on 8/22/2017.
 */

public class ExpensesSummaryAdapter extends RecyclerView.Adapter<ExpensesSummaryAdapter.CustomViewHolder> {

    String description;
    String serviceCost;
    String serviceName;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private List<Expenses> paymentsList;
    private Context _context;

    public ExpensesSummaryAdapter(Context ctx, List<Expenses> offeredServicesList) {
        this._context = ctx;
        this.paymentsList = offeredServicesList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_history_list_content, null);
        ExpensesSummaryAdapter.CustomViewHolder viewHolder = new ExpensesSummaryAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final String amount = paymentsList.get(position).amount;
        final String type = paymentsList.get(position).type;

        holder.txtAmount.setText(amount);
        holder.txtType.setText(type);

    }

    @Override
    public int getItemCount() {
        return paymentsList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtType;
        public final TextView txtAmount;

        public CustomViewHolder(View view) {
            super(view);
            mView = view;
            txtType = (TextView) view.findViewById(R.id.txtAccountRef);
            txtAmount = (TextView) view.findViewById(R.id.txtPaymentRef);
        }
    }
}
