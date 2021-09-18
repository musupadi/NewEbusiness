package com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC;



import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class ConditionData {
    public static String[][] data = new String[][]{
            {"1",
                    "Rumah sakit telah beroperasional selama >10 tahun",
                    "CONDITION",
                    "NOPE",
                    "YA"
            },
            {"2",
                    "Terdapat pelayanan kesehatan tingkat pertama (puskesmas/ klinik) di sekitar rumah sakit (radius 2 km)",
                    "CONDITION",
                    "NOPE",
                    "YA"
            },
            {"3",
                    "Rumah Sakit memiliki fasilitas pelayanan yang lengkap/ khusus sesuai dengan klasifikasi rumah sakit tersebut",
                    "CONDITION",
                    "NOPE",
                    "YA"
            },
            {"4",
                    "Kebutuhan tempat tidur di wilayah tersebut menunjukkan tanda negative (-) ",
                    "CONDITION",
                    "Simulasi Perhitungan Tempat Tidur",
                    "YA"
            },
            {"5",
                    "Lokasi rumah sakit strategis dan mudah dijangkau (berada di pinggir jalan besar, berada disudut persimpangan jalan, banyak angkutan umum yang melewati rumah sakit tersebut)",
                    "CONDITION",
                    "NOPE",
                    "YA"
            },
            {"6",
                    "Rumah sakit memiliki layanan unggulan yang sesuai dengan sepuluh penyakit utama di wilayah tersebut.",
                    "CONDITION",
                    "NOPE",
                    "YA"
            },
            {"7",
                    "Rumah sakit memiliki ketersediaan utilitas public yang mencukupi (air bersih, jaringan air kotor, listrik, jalur komunikasi, lahan parkir)",
                    "CONDITION",
                    "NOPE",
                    "YA"
            },
            {"8",
                    "Indikator kinerja operasional RS sesuai standar kemenkes",
                    "CONDITION",
                    "Simulasi Perhitungan Indikator Kinerja RS",
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
