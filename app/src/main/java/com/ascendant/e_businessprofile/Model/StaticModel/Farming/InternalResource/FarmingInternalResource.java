package com.ascendant.e_businessprofile.Model.StaticModel.Farming.InternalResource;

import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class FarmingInternalResource {
    public static String[][] data = new String[][]{
            {"1",
                    "Home",
                    "false"
            },
            {"2",
                    "Farming",
                    "false"
            },
            {"3",
                    "Internal Resource",
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
