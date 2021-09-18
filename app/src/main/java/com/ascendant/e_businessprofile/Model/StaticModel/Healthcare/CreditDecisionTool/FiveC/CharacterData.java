package com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC;


import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class CharacterData {
    public static String[][] data = new String[][]{
            {"1",
                    "Rumah sakit memiliki visi, misi, value dan nilai budaya kerja yang jelas dan diterapkan dalam setiap proses bisnis RS",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"2",
                    "Rumah sakit memiliki struktur organisasi dengan penganggung jawab memiliki latar belakang pendidikan/ pengalaman sesuai dengan bagiannya. Misal : Kepala Instalasi Farmasi adalah seorang Apoteker; Kepala Ruang Rawat Inap adalah seorang NERS; dsb.",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"3",
                    "Rumah sakit pernah terlibat dalam pemberitaan terkait pelayanan yang kurang baik (malpraktik/ tidak ramah)",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"4",
                    "Rumah Sakit memiliki komisaris dan/ atau direksi yang bekerja di dunia perumahsakitan <20th ",
                    "CHARACTER",
                    "NOPE",
                    "TIDAK"
            },
            {"5",
                    "Rumah sakit memiliki Standar Operasional Prosedur disetiap kegiatan bisnis",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"6",
                    "Rumah sakit pernah putus kerja sama dengan pihak ketiga yang disebabkan kelalaian manajemen rumah sakit dalam mengelola rumah sakit",
                    "CHARACTER",
                    "NOPE",
                    "TIDAK"
            },
            {"7",
                    "Pemeriksaan hasil SLIK Checking menunjukkan hasil “kualitas : 1 – lancar”",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"8",
                    "Pemeriksaan rekening koran rumah sakit, menunjukkan total mutasi kredit 70 – 80% dari penjualan ",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"9",
                    "Manajemen memiliki rencana bisnis rumah sakit untuk jangka panjang – menengah – pendek",
                    "CHARACTER",
                    "NOPE",
                    "YA"
            },
            {"10",
                    "Rumah Sakit di audit secara berkala dari pihak eksternal maupun internal",
                    "CHARACTER",
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
