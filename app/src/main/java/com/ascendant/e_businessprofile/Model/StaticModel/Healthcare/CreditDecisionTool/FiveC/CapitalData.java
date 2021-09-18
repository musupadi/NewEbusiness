package com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC;



import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class CapitalData {
    public static String[][] data = new String[][]{
            {"1",
                    "Rumah Sakit dikelola atau dimiliki oleh pemerintah pusat/pemerintah daerah/swasta",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"2",
                    "Rumah sakit berbadan hukum (Perseroan terbatas atau yayasan)",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"3",
                    "Rumah Sakit swasta memiliki akta pendirian perusahaan yang menunjukkan secara rinci komposisi kepemilikan modal yang tertanam dalam rumah sakit tersebut.",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"4",
                    "Modal terbesar dimiliki oleh seorang yang memiliki latar belakang dibidang bisnis saja, tanpa memiliki latar belakang ilmu pelayanan kesehatan",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"5",
                    "Rumah Sakit memiliki izin mendirikan bangunan dan/ atau izin operasional yang masih aktif",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"6",
                    "Setiap tenaga kesehatan di rumah sakit, memiliki surat izin praktik atau surat izin kerja tenaga kesehatan.",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"7",
                    "Rumah Sakit terakreditasi SNARS (Standar Nasional Akreditasi Rumah Sakit) dengan tingkat paripurna atau terakreditasi internasional (JCI).",
                    "CAPITAL",
                    "NOPE",
                    "YA"
            },
            {"8",
                    "Rumah sakit mengalami perubahan nama – nama pemilik modal yang tercantum dalam akta pendirian yang disebabkan karena perselisihan antar pemilik modal.",
                    "CAPITAL",
                    "NOPE",
                    "TIDAK"
            },
            {"9",
                    "Pekerja di rumah sakit didominasi oleh pekerja usia 35th keatas",
                    "CAPITAL",
                    "NOPE",
                    "TIDAK"
            },
            {"10",
                    "Mayoritas pasien yang berkunjung ke RS adalah Peserta JKN – KIS",
                    "CAPITAL",
                    "NOPE",
                    "TIDAK"
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
