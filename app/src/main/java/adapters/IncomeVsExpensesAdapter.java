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

import model.IncomeExpensesModel;

/**
 * Created by RONIN on 8/22/2017.
 */

public class IncomeVsExpensesAdapter extends RecyclerView.Adapter<IncomeVsExpensesAdapter.CustomViewHolder> {

    String description;
    String serviceCost;
    String serviceName;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private List<IncomeExpensesModel> incomeExpensesModelList;
    private Context _context;

    public IncomeVsExpensesAdapter(Context ctx, List<IncomeExpensesModel> offeredServicesList) {
        this._context = ctx;
        this.incomeExpensesModelList = offeredServicesList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_vs_expenses_content, null);
        IncomeVsExpensesAdapter.CustomViewHolder viewHolder = new IncomeVsExpensesAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final String totalExpenses = incomeExpensesModelList.get(position).totalExpenses;
        final String totalIncome = incomeExpensesModelList.get(position).totalIncome;
        final String difference = incomeExpensesModelList.get(position).difference;

        holder.txtTotalExpense.setText(totalExpenses);
        holder.txtTotalIncome.setText(totalIncome);
        holder.txtDifference.setText(difference);

    }

    @Override
    public int getItemCount() {
        return incomeExpensesModelList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtTotalIncome;
        public final TextView txtTotalExpense;
        public final TextView txtDifference;

        public CustomViewHolder(View view) {
            super(view);
            mView = view;
            txtTotalIncome = (TextView) view.findViewById(R.id.txtTotalIncome);
            txtTotalExpense = (TextView) view.findViewById(R.id.txtTotalExpenses);
            txtDifference = (TextView) view.findViewById(R.id.txtDifference);
        }
    }
}
