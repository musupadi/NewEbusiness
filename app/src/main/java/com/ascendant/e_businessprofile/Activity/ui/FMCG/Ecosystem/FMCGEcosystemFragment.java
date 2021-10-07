package com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Association.FMCGAssociationActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Players.FMCGEcosystemPlayersActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.EcosystemFragment;
import com.ascendant.e_businessprofile.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FMCGEcosystemFragment extends Fragment {

    PDFView photoView;
    CardView Distributor,Retail,ECommerce,Players,Association,Manufacture,Supplier;
    Dialog myDialog;
    Ascendant AscNet;
    Button Views,Download;
    public FMCGEcosystemFragment() {
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
        return inflater.inflate(R.layout.fragment_f_m_c_g_ecosystem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AscNet = new Ascendant();


        Distributor = view.findViewById(R.id.cardDistributor);
        Retail = view.findViewById(R.id.cardRetail);
        ECommerce = view.findViewById(R.id.cardECommerce);
        Players = view.findViewById(R.id.cardPlayers);
        Association = view.findViewById(R.id.cardAssociation);
        Manufacture = view.findViewById(R.id.cardManufacture);
        Supplier = view.findViewById(R.id.cardSupplier);
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_view_download);
        Views = myDialog.findViewById(R.id.btnView);
        Download = myDialog.findViewById(R.id.btnDownload);
        photoView = view.findViewById(R.id.ivEcosystem);
        new RetreivePDFStreams().execute("https://ebuss.fabakonsultan.com/files/healthcare/ekosistem/map_ekosistem.pdf");
        Distributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Views.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/fmcg/distributor.pdf"));
                        startActivity(browserIntent);
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
                                        AscNet.DownloadPDF("https://fabakonsultan.com/uploads/fmcg/distributor.pdf","Distributor FMCG",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.print_primary);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        Retail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Views.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/fmcg/retail.pdf"));
                        startActivity(browserIntent);
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
                                        AscNet.DownloadPDF("https://fabakonsultan.com/uploads/fmcg/retail.pdf","Retail FMCG",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.print_primary);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        ECommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Views.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/fmcg/e-commerce.pdf"));
                        startActivity(browserIntent);
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
                                        AscNet.DownloadPDF("https://fabakonsultan.com/uploads/fmcg/e-commerce.pdf","E-Commerce FMCG",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.print_primary);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        Players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FMCGEcosystemPlayersActivity.class);
                startActivity(intent);
            }
        });
        Association.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FMCGAssociationActivity.class);
                startActivity(intent);
            }
        });
        Manufacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Views.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/fmcg/ekosistem-manufaktur.pdf"));
                        startActivity(browserIntent);
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
                                        AscNet.DownloadPDF("https://fabakonsultan.com/uploads/fmcg/ekosistem-manufaktur.pdf","Manufacture FMCG",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.print_primary);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
        Supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Views.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/fmcg/ekosistem-suplier.pdf"));
                        startActivity(browserIntent);
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
                                        AscNet.DownloadPDF("https://fabakonsultan.com/uploads/fmcg/ekosistem-suplier.pdf","Supplier FMCG",getActivity());
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.print_primary);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }
        });
    }
    class RetreivePDFStreams extends AsyncTask<String,Void, InputStream> {
        InputStream inputStream;
        @Override
        protected InputStream doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            photoView.fromStream(inputStream).load();
        }
    }
}