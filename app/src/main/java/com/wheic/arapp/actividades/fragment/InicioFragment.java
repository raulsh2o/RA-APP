//package com.example.simulation.actividades.fragment;
package com.wheic.arapp.actividades.fragment;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/*
import com.example.simulation.Interfaces.IComunicaFragments;
import com.example.simulation.R;
import com.example.simulation.Interfaces.IComunicaFragments;
import com.example.simulation.MainActivity;
*/

import com.wheic.arapp.Interfaces.IComunicaFragments;
import com.wheic.arapp.R;
import com.wheic.arapp.Interfaces.IComunicaFragments;
import com.wheic.arapp.MainActivity;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    View vista;
    Activity actividad;
    CardView cardSalvajes,cardDomesticos,cardFrutas,cardVocales, cardNumeros;
    //ImageView btn_settings;
    IComunicaFragments interfaceComunicaFragments;


    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        cardSalvajes = vista.findViewById(R.id.cardSalvajes);
        cardDomesticos = vista.findViewById(R.id.cardDomesticos);
        cardVocales = vista.findViewById(R.id.cardVocales);
        cardFrutas=vista.findViewById(R.id.cardFrutas);
        cardNumeros = vista.findViewById(R.id.cardNumeros);
        eventosMenu();

        return vista;
    }

    private void eventosMenu() {

        cardSalvajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interfaceComunicaFragments.salvajes();

            }
        });
        cardDomesticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interfaceComunicaFragments.domesticos();

            }
        });
        cardVocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interfaceComunicaFragments.vocales();

            }
        });
        cardFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicaFragments.frutas();
                //Toast.makeText(getContext(),"Iniciar Juego desde el fragment",Toast.LENGTH_SHORT).show();
            }
        });
        cardNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicaFragments.numeros();
                //Toast.makeText(getContext(),"Iniciar Juego desde el fragment",Toast.LENGTH_SHORT).show();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.actividad=(Activity) context;
            interfaceComunicaFragments= (IComunicaFragments) actividad;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}