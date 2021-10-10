package com.ascendant.e_businessprofile.Adapter.Static;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ModuleActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence.BusinessRefrenceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence.RegulationActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence.eBookActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance.ComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.CrreditDecisionToolActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.InvestmentCredit.InvestmentCreditActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.RequirementAnalysisActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.WorkingCapitalCredit.WorkingCapitalCreditActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.CreditWorthinessActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalFinanceIndicator.HospitalFinanceIndicatorActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalFinanceIndicator.ParametersHospitalFinanceIndicatorActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalFinanceIndicator.ResultHospitalFinanceIndicatorActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator.HospitalOperationalPerfomanceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator.ParameterHospitalOperationalActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator.SimulationHospitalOperationalActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalRequirementRatio.HospitalRequirementRatioActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.HospitalAssociation.HospitalAssociationActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital.ListOfHospitalActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.SupportingIndustries.HospitalEquipmentActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.SupportingIndustries.SupportingInduestriesActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.ListOfProbing.ListOfProbingActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterNavigator extends RecyclerView.Adapter<AdapterNavigator.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    public AdapterNavigator(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_navigation,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        int i = posistion;
        final DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.Navigator.setText(dm.getNavigator());
        if (dm.getONCLICK().equals("true")){
            holderData.back.setImageResource(R.drawable.chevron_left);
            Typeface typeface = Typeface.createFromAsset(
                    ctx.getAssets(),
                    "inter_extra_bold.ttf");
            holderData.Navigator.setTypeface(typeface);
        }else{
            holderData.back.setImageResource(R.drawable.chevron_left_concerate);
            Typeface typeface = Typeface.createFromAsset(
                    ctx.getAssets(),
                    "inter.ttf");
            holderData.Navigator.setTypeface(typeface);
        }
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dm.getNavigator().equals("Home")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent intent = new Intent(ctx, HomeActivity.class);
                        ctx.startActivity(intent);
                    }
                }else if (dm.getNavigator().equals("FMCG")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, ModuleActivity.class);
                        i.putExtra("MODULE", "FMCG");
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Business Refrences")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, BusinessRefrenceActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("e-Book")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, eBookActivity.class);
                        i.putExtra("MODULE", "ebook");
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Newsletter")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, eBookActivity.class);
                        i.putExtra("MODULE", "newsletter");
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Business Review")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, eBookActivity.class);
                        i.putExtra("MODULE", "business_review");
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Regulations")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, RegulationActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("List of Probing")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, ListOfProbingActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Compliance")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, ComplianceActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("News")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, ComplianceActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Ecosystem")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, ComplianceActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Supporting Industries")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, SupportingInduestriesActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Hospital Equipment")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, HospitalEquipmentActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("List of Hospital")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, ListOfHospitalActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Hospital Association")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, HospitalAssociationActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Credit Worthiness")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, CreditWorthinessActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Hospital Operational Perfomance Indicator")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, HospitalOperationalPerfomanceActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Parameters")){
                    if (mList.get(i-1).equals("Hospital Operational Perfomance Indicator")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, ParameterHospitalOperationalActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Simulation")) {
                    if (mList.get(i - 1).equals("Hospital Operational Perfomance Indicator")) {
                        if (!dm.getONCLICK().equals("true")) {
                            Intent i = new Intent(ctx, SimulationHospitalOperationalActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Simulation")) {
                    if (mList.get(i - 1).equals("Hospital Operational Perfomance Indicator")) {
                        if (!dm.getONCLICK().equals("true")) {
                            Intent i = new Intent(ctx, SimulationHospitalOperationalActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Input Simulation")) {
                    if (mList.get(i - 1).equals("Hospital Operational Perfomance Indicator")) {
                        if (!dm.getONCLICK().equals("true")) {
                            Intent i = new Intent(ctx, HospitalOperationalPerfomanceActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Hospital Finance Indicator")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, HospitalFinanceIndicatorActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Parameters")){
                    if (mList.get(i-1).equals("Hospital Finance Indicator")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, ParametersHospitalFinanceIndicatorActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Simulation")){
                    if (mList.get(i-1).equals("Hospital Finance Indicator")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, SimulationHospitalOperationalActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                } else if (dm.getNavigator().equals("Result")){
                    if (mList.get(i-2).equals("Hospital Finance Indicator")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, ResultHospitalFinanceIndicatorActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Hospital Requirement Ratio")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, HospitalRequirementRatioActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Credit Decision Tool")){
                    if (!dm.getONCLICK().equals("true")){
                        Intent i = new Intent(ctx, CrreditDecisionToolActivity.class);
                        ctx.startActivity(i);
                    }
                }else if (dm.getNavigator().equals("Inveastment Credit")){
                    if (mList.get(i-3).equals("Healthcare")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, InvestmentCreditActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Workingn Capital Credit")){
                    if (mList.get(i-3).equals("Healthcare")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, WorkingCapitalCreditActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }else if (dm.getNavigator().equals("Requirement Analysis")){
                    if (mList.get(i-2).equals("Healthcare")){
                        if (!dm.getONCLICK().equals("true")){
                            Intent i = new Intent(ctx, RequirementAnalysisActivity.class);
                            ctx.startActivity(i);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Navigator;
        LinearLayout card;
        ImageView back;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearNavigator);
            Navigator = v.findViewById(R.id.tvNavigator);
            back = v.findViewById(R.id.ivBack);
        }
    }
}
