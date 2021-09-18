package com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC;



import com.ascendant.e_businessprofile.Model.DataModel;

import java.util.ArrayList;

public class CapacityData {
    public static String[][] data = new String[][]{
            {"1",
                    "Bed Occupancy Rate rumah sakit antara 60 – 80% (proyeksi BOR bagi RS Baru)",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"2",
                    "Average Length Of Stay rumah sakit antara 6 – 9 hari (Proyeksi AVLOS bagi RS Baru)",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"3",
                    "Kepemilikan tempat usaha (tanah) yaitu milik sendiri (atas nama rumah sakit).",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"4",
                    "Laporan keuangan menunjukkan adanya laba ditahan yang minus (-)",
                    "CAPACITY",
                    "NOPE",
                    "TIDAK"
            },
            {"5",
                    "Modal RS selama 3 (Tiga) tahun terakhir meningkat",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"6",
                    "Laporan keuangan RS 3 (tiga) tahun terakhir menunjukkan laba",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"7",
                    "Revenue (Pendapatan) RS 3 (tiga) tahun terakhir meningkat",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"8",
                    "Rumah sakit tidak pernah mengalami rugi selama beroperasional ",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"9",
                    "Current Ratio RS menunjukkan hasil lebih besar dari 1,5",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"10",
                    "Quick Ratio RS menunjukkan hasil lebih besar dari 1",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"11",
                    "Cash Ratio RS menunjukkan hasil lebih besar  dari 0,5",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"12",
                    "Gross Profit Margin menunjukkan lebih besar dari Tahun Sebelumnya",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"13",
                    "Return on Asset menunjukkan lebih besar dari Tahun Sebelumnya",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"14",
                    "Return on Equity menunjukkan lebih besar dari Tahun Sebelumnya",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"15",
                    "Return on Investment menunjukkan lebih besar dari Tahun Sebelumnya",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"16",
                    "Dept to Equity Ratio atau Leverage menunjukkan lebih kecil dari 3x ",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"17",
                    "AR DOH (Perputaran Piutang) menunjukkan 72 hari",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"18",
                    "IN DOH (Perputaran Persediaan) menunjukkan 14 hari",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"19",
                    "AP DOH menunjukkan (Perputaran Utang) 30 hari",
                    "CAPACITY",
                    "NOPE",
                    "YA"
            },
            {"20",
                    "Repayment Capacity menunjukkan hasil lebih besar dari 2x",
                    "CAPACITY",
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
