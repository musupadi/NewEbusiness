package com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool;

import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class FMCGCreditDecisionToolNFNBResultFinancialStatementAnalysisModel {
    public static String[][] data = new String[][]{
            {"1",
                    "Home",
                    "false"
            },
            {"2",
                    "FMCG",
                    "false"
            },
            {"3",
                    "Credit Decision Tool",
                    "false"
            },
            {"4",
                    "Non Food & Beverage",
                    "false"
            },
            {"5",
                    "Result",
                    "true"
            }
    };
    public static ArrayList<DataModel> getListData(){
        DataModel model = null;
        ArrayList<DataModel> list = new ArrayList<>();
        for (String[] aData : data) {
            model = new DataModel();
            model.setIdNavigator(aData[0]);
            model.setNavigator(aData[1]);
            model.setONCLICK(aData[2]);
            list.add(model);
        }

        return list;
    }
}
