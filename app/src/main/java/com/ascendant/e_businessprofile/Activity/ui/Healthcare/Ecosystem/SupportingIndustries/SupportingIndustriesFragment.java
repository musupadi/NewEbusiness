package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.SupportingIndustries;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.R;


public class SupportingIndustriesFragment extends Fragment {
    CardView Pharmacy,HealthInsurance,ClinicalLab,MedicalWasteManagement,ThirdPartyAdmin,MedicalDevicesSupplies,BPJS,Clinic,HospitalEquipment;
    Dialog myDialog;
    Ascendant ascendant;
    Button View,Download;
    public SupportingIndustriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supporting_industries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Pharmacy = view.findViewById(R.id.cardPharmacy);
        HealthInsurance = view.findViewById(R.id.healthInsurance);
        ClinicalLab = view.findViewById(R.id.cardClinicLab);
        MedicalWasteManagement = view.findViewById(R.id.cardMedicalWasteManagement);
        ThirdPartyAdmin = view.findViewById(R.id.cardThirdPartyAdministrator);
        MedicalDevicesSupplies = view.findViewById(R.id.cardMedicalDeviceSuppliers);
        BPJS = view.findViewById(R.id.cardBPJS);
        Clinic = view.findViewById(R.id.cardClinic);
        HospitalEquipment = view.findViewById(R.id.cardHospitalEquipment);

        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_view_download);
        View = myDialog.findViewById(R.id.btnView);
        Download = myDialog.findViewById(R.id.btnDownload);
        ascendant = new Ascendant();

        Pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), PortraitWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.the-urbandev.com/healthcare/page/ekosistem/Farmasi_PBF.php");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_Farmasi_PBF.pdf","Pharmaceutical Wholesaler",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        HealthInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), PortraitWebViewEbookActivity.class);
                        i.putExtra("LINK", "          ");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_Asuransi.pdf","Health Insurance",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        ClinicalLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.the-urbandev.com/healthcare/page/ekosistem/Laboratorium_Klinik.php");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/ekosistem/hospital/Ekosistem_RS_Alkes.pdf","Clinical Labolatory",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        MedicalWasteManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.the-urbandev.com/healthcare/page/ekosistem/Jasa_Pengelola_Limbah_Medis.php");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_Jasa_Pengelola_Limbah_Medis.pdf","Medical Waste Management Service",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        ThirdPartyAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.the-urbandev.com/healthcare/page/ekosistem/Third_Party_Administrator.php");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_Klinik.pdf","Third Party Administrator",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        MedicalDevicesSupplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Download File ?")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_Laboratorium_Klinik.pdf","Medical Device Supplies",getActivity());
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Set your icon here
                        .setTitle("Perhatian !!!")
                        .setIcon(R.drawable.ic_baseline_print_24);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.the-urbandev.com/healthcare/page/ekosistem/Klinik.php");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_Klinik.pdf","Clinic",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });


            }
        });
        BPJS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.the-urbandev.com/healthcare/page/ekosistem/BPJS.php");
                        startActivity(i);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ascendant.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/ekosistem/Ekosistem_RS_BPJS.pdf","BPJS",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        HospitalEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(getActivity(),HospitalEquipmentActivity.class);
                startActivity(intent);
            }
        });
    }
}