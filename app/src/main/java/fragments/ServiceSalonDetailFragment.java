package fragments;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lillian.lillysbeauty.R;
import com.lillian.lillysbeauty.ServiceSalonDetailActivity;
import com.lillian.lillysbeauty.ServiceSalonListActivity;

import model.OfferedServices;

/**
 * A fragment representing a single Salon detail screen.
 * This fragment is either contained in a {@link ServiceSalonListActivity}
 * in two-pane mode (on tablets) or a {@link ServiceSalonDetailActivity}
 * on handsets.
 */
public class ServiceSalonDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String OFFERED_SERVICE_ID = "OFFERED_SERVICE_ID";
    public static final String SELECTED_SERVICES = "SELECTED_SERVICES";
    public static final String SERVICE_NAME = "SERVICE_NAME";
    public static final String SERVICE_COST = "SERVICE_COST";
    public static final String SALON_NAME = "SALON_NAME";
    public static final String SERVICE_DESCRIPTION = "SERVICE_DESCRIPTION";

    String offeredserviceID;
    String serviceName;
    String serviceCost;
    String salonName;
    String description;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ServiceSalonDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(OFFERED_SERVICE_ID)) {
            offeredserviceID = getArguments().getString(OfferedServices.OFFERED_SERVICE_ID_ARG);
            serviceName = getArguments().getString(OfferedServices.SERVICE_NAME_ARG);
            serviceCost = getArguments().getString(OfferedServices.SERVICE_COST_ARG);
            salonName = getArguments().getString(OfferedServices.SALON_NAME_ARG);
            description = getArguments().getString(OfferedServices.SERVICE_DESCRIPTION_ARG);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(serviceName);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.salon_detail, container, false);

        // Show the dummy content as text in a TextView.
        //if (mItem != null) {
        ((TextView) rootView.findViewById(R.id.salon_name)).setText(salonName);
        ((TextView) rootView.findViewById(R.id.service_name)).setText(serviceName);
        ((TextView) rootView.findViewById(R.id.service_cost)).setText(serviceCost);
        ((TextView) rootView.findViewById(R.id.description)).setText(description);
        // }

        return rootView;
    }
}
