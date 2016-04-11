package es.ucm.as_tutor.presentacion;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import es.ucm.as_tutor.R;


public class FragmentListadoEvento extends Fragment {
    private Evento[] datos =
            new Evento[]{
                    new Evento("Evento 1", "1 feb", "1 agosto"),
                    new Evento("Evento 2", "1 mar", "1 junio"),
                    new Evento("Evento 3", "1 abril", "1 dic"),
                    new Evento("Evento 4", "1 mayo", "1 nov")};
    private ListView listadoEvento;

    private OnFragmentInteractionListener mListener;

    public FragmentListadoEvento() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listadoEvento = (ListView)getView().findViewById(R.id.ListadoEventos);

        listadoEvento.setAdapter(new AdaptadorEventos(this, datos));

        listadoEvento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
              /*  if (listener != null) {
                    listener.onUsuarioSeleccionado(
                            (Usuario) lstListado.getAdapter().getItem(pos));
                }*/
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_evento, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
