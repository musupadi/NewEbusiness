package com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC;



import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class  CollateralData {
    public static String[][] data = new String[][]{
            {"1",
                    "Lokasi rumah sakit strategis dan mudah dijangkau (berada di pinggir jalan besar, berada disudut persimpangan jalan, banyak angkutan umum yang melewati rumah sakit tersebut)",
                    "COLLATERAL",
                    "NOPE",
                    "YA"
            },
            {"2",
                    "Rumah sakit dalam masalah sengketa",
                    "COLLATERAL",
                    "NOPE",
                    "YA"
            },
            {"3",
                    "Nilai agunan/ jaminan bersifat solid (properti) dan memenuhi kebutuhan kredit ya diajukan",
                    "COLLATERAL",
                    "NOPE",
                    "YA"
            },
            {"4",
                    "Status agunan/ jaminan a.n. direktur rumah sakit",
                    "COLLATERAL",
                    "NOPE",
                    "YA"
            },
            {"5",
                    "Agunan bersertifikat hak milik",
                    "COLLATERAL",
                    "NOPE",
                    "YA"
            }
    };
    public static ArrayList<DataModel> getListData(){
        DataModel models = null;
        ArrayList<DataModel> list = new ArrayList<>();
        for (String[] aData : data) {
            models = new DataModel();
            models.setNo_quis(aData[0]);
            models.setSoal_quis(aData[1]);
            models.setCategory_quis(aData[2]);
            models.setSimulasi(aData[3]);
            models.setJawaban(aData[4]);
            list.add(models);
        }
        return list;
    }
}
