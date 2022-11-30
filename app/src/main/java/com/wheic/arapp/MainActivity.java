package com.wheic.arapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.ArrayList;
import java.util.List;

import com.wheic.arapp.Interfaces.IComunicaFragments;
import com.wheic.arapp.actividades.DomesticosActivity;
import com.wheic.arapp.actividades.FrutasActivity;
import com.wheic.arapp.actividades.NumerosActivity;
import com.wheic.arapp.actividades.VocalesActivity;
import com.wheic.arapp.actividades.SalvajesActivity;
import com.wheic.arapp.actividades.fragment.InicioFragment;

public class MainActivity extends AppCompatActivity implements IComunicaFragments,InicioFragment.OnFragmentInteractionListener {

    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    //****************************************************************
    private List<AnchorNode> anchorNodeList = new ArrayList<>();
    private AnchorNode currentSelectedAnchorNode = null;
    private ModelRenderable tiburon1;
    private ModelRenderable gato1;
    private ModelRenderable iguana2;
    private int Status1 = 0;
    //****************************************************************
    Fragment fragmentInicio;
    //****************************************************************
    Button bdomesticos, bsalvajes,bvocales,bfrutas,bnumeros;
    //****************************************************************
    View vista;
    CardView cardDomesticos;
    //****************************************************************
    //View vista;
    //CardView cardSalvajes,cardDomesticos,cardFrutas,cardVocales, cardNumeros;
    //ImageView btn_settings;
    //IComunicaFragments interfaceComunicaFragments;
    //*****************************************************************
   /* */
    // https://www.geeksforgeeks.org/how-to-build-a-simple-augmented-reality-android-app/
    // https://blog.ldtalentwork.com/2021/05/06/how-to-create-a-google-ar-core-sceneform-animation-in-android/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //protected View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //arCam.getArSceneView().getScene().setOnPeekTouchListener(this::handleOnTouch);
       //*************************************************************************
        fragmentInicio = new InicioFragment();
       // getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFrangments,fragmentInicio).commit();
       //getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
       //**************************************************************************
        bdomesticos = (Button)findViewById(R.id.domesticos);
        bsalvajes = (Button)findViewById(R.id.asalvaje);
        bvocales = (Button)findViewById(R.id.ivocales);
        bfrutas = (Button)findViewById(R.id.iFrutas);
        bnumeros=(Button)findViewById(R.id.inumeros);
        //*************************************************************************
        //vista = inflater.inflate(R.layout.activity_main,container,false);
        //*************************************************************************
        /*String file ="MainActivity0";
        Toast toast = Toast.makeText(this, file, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();*/
       //****************************************************
       //getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
       //getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
       //*************************************************************************
       //*************************************************************************
       /* */
       /* String file1 ="MainActivity0";
        Toast toast1 = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
        String file2 ="MainActivityS";
        Toast toast2 = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
        */
        //*************************************************************************
        //Toast toast = Toast.makeText(this, file, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        //toast.show();
        //Intent intentDo = new Intent(this, DomesticosActivity.class);
        Intent intentDo = new Intent(this, dActivity.class);
        Intent intentSa = new Intent(this, SalvajesA.class);
        Intent intentVo = new Intent(this, VocalesA.class);
        Intent intentFr = new Intent(this, FrutasA.class);
        Intent intentNu = new Intent(this, NumerosA.class);
        bdomesticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*****************************************************
                //file ="MainActivity1";
                //Toast toast = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
                /*toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();*/
                //Intent intent = new Intent(this, DomesticosActivity.class);
                startActivity(intentDo);
            }
        });
        bsalvajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*****************************************************
                //file ="MainActivity1";
                //Toast toast = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
                /*toast2.setGravity(Gravity.CENTER, 0, 0);
                toast2.show();*/
                //Intent intent = new Intent(this, DomesticosActivity.class);
                startActivity(intentSa);
            }
        });

        bvocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*****************************************************
                //file ="MainActivity1";
                //Toast toast = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
                /*toast2.setGravity(Gravity.CENTER, 0, 0);
                toast2.show();*/
                //Intent intent = new Intent(this, DomesticosActivity.class);
                startActivity(intentVo);
            }
        });
        bfrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*****************************************************
                //file ="MainActivity1";
                //Toast toast = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
                /*toast2.setGravity(Gravity.CENTER, 0, 0);
                toast2.show();*/
                //Intent intent = new Intent(this, DomesticosActivity.class);
                startActivity(intentFr);
            }
        });
        bnumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*****************************************************
                //file ="MainActivity1";
                //Toast toast = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
                /*toast2.setGravity(Gravity.CENTER, 0, 0);
                toast2.show();*/
                //Intent intent = new Intent(this, DomesticosActivity.class);
                startActivity(intentNu);
            }
        });
       //*****************************************************
        /*file ="MainActivity1";
        toast = Toast.makeText(this, file, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();*/
       //****************************************************
       //*************************************************************************
/*
*/
        //***********************************************************************
        //FloatingActionButton gato1show = findViewById(R.id.id_gato1);
       /* */
        //************************************************************************
       //return vista;
    }

    public void salvajes() {
        Intent intent = new Intent(this, SalvajesActivity.class);
        startActivity(intent);
    }
    public void domesticos() {
        Intent intent = new Intent(this, DomesticosActivity.class);
        startActivity(intent);
    }
    public void vocales() {
        Intent intent = new Intent(this, VocalesActivity.class);
        startActivity(intent);
    }
    public void frutas() {
        Intent intent = new Intent(this, FrutasActivity.class);
        startActivity(intent);
    }
    public void numeros() {
        Intent intent = new Intent(this, NumerosActivity.class);
        startActivity(intent);
    }
    public void onFragmentInteraction(Uri uri){

    }
    /*
    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {
        //************************************************************************
        //Remove an anchor node
        //************************************************************************
        AnchorNode anchorNode = new AnchorNode(anchor);
        // Creating a AnchorNode with a specific anchor
        anchorNode.setParent(arCam.getArSceneView().getScene());
        //attaching the anchorNode with the ArFragment

        //****************************************************
        //AnchorNode addedAnchorNode = new AnchorNode(newMarkAnchor);
        //addedAnchorNode.setRenderable(anchorNode);
        //addAnchorNode(addedAnchorNode);
        //currentSelectedAnchorNode = addedAnchorNode;
        //****************************************************
        TransformableNode model = new TransformableNode(arCam.getTransformationSystem());
        model.setParent(anchorNode);
        //attaching the anchorNode with the TransformableNode
        model.setRenderable(modelRenderable);
        //attaching the 3d model with the TransformableNode that is already attached with the node
        model.select();

        //********************************************************
        currentSelectedAnchorNode = anchorNode;
        //*********************************************************

    }*/
/*
    private void removeAnchorNode(AnchorNode nodeToremove) {
        //*********************************************************************
        String file11 ="remove Anchor Node";
        Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
        toast11.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast11.show();
        //*********************************************************************
        //Remove an anchor node
        if (nodeToremove != null) {
            //*********************************************************************
            String file12 ="nodeToremove != null";
            Toast toast12 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
            toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast12.show();
            //*********************************************************************
            arCam.getArSceneView().getScene().removeChild(nodeToremove);
            //anchorNodeList.remove(nodeToremove);
            nodeToremove.getAnchor().detach();
            nodeToremove.setParent(null);
            nodeToremove = null;
            //numberOfAnchors--;
            //Toast.makeText(LineViewMainActivity.this, "Test Delete - markAnchorNode removed", Toast.LENGTH_SHORT).show();
       } else {
            //Toast.makeText(LineViewMainActivity.this, "Delete - no node selected! Touch a node to select it.", Toast.LENGTH_SHORT).show();
        }
    }*/
/*
    private AnchorNode moveRenderable(AnchorNode markAnchorNodeToMove, Pose newPoseToMoveTo) {
        //Move a renderable to a new pose
        if (markAnchorNodeToMove != null) {
            arCam.getArSceneView().getScene().removeChild(markAnchorNodeToMove);
            anchorNodeList.remove(markAnchorNodeToMove);
        } else {
            //Log.d(TAG,"moveRenderable - markAnchorNode was null, the little £$%^...");
            return null;
        }
        Frame frame = arCam.getArSceneView().getArFrame();
        Session session = arCam.getArSceneView().getSession();
        Anchor markAnchor = session.createAnchor(newPoseToMoveTo.extractTranslation());
        AnchorNode newMarkAnchorNode = new AnchorNode(markAnchor);

        switch(Status1)
        {
            case 1:
                newMarkAnchorNode.setRenderable(gato1);
            break;

            case 2:
                newMarkAnchorNode.setRenderable(iguana2);
            break;

            default:
            break;
        }

        newMarkAnchorNode.setParent(arCam.getArSceneView().getScene());
        anchorNodeList.add(newMarkAnchorNode);

        //Delete the line if it is drawn
        //removeLine(nodeForLine);

        return newMarkAnchorNode;
    }*/
/*
    private void handleOnTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
        //Log.d(TAG,"handleOnTouch");
        // First call ArFragment's listener to handle TransformableNodes.
        arCam.onPeekTouch(hitTestResult, motionEvent);

        //We are only interested in the ACTION_UP events - anything else just return
        if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
            return;
        }

        // Check for touching a Sceneform node
        if (hitTestResult.getNode() != null) {
           // Log.d(TAG,"handleOnTouch hitTestResult.getNode() != null");
            //Toast.makeText(LineViewMainActivity.this, "hitTestResult is not null: ", Toast.LENGTH_SHORT).show();
            Node hitNode = hitTestResult.getNode();

            if (hitNode.getRenderable() == gato1) {
                //Toast.makeText(LineViewMainActivity.this, "We've hit Andy!!", Toast.LENGTH_SHORT).show();
                //First make the current (soon to be not current) selected node not highlighted
                if (currentSelectedAnchorNode != null) {
                    currentSelectedAnchorNode.setRenderable(gato1);
                }
                //Now highlight the new current selected node
                ModelRenderable highlightedAndyRenderable = gato1.makeCopy();
                highlightedAndyRenderable.getMaterial().setFloat3("baseColorTint", new Color(android.graphics.Color.rgb(255,0,0)));
                hitNode.setRenderable(highlightedAndyRenderable);
                currentSelectedAnchorNode = (AnchorNode) hitNode;
            }
            return;
        } else{
            // Place the anchor 1m in front of the camera. Make sure we are not at maximum anchor first.
            //Log.d(TAG,"adding Andy in fornt of camera");
            //if (numberOfAnchors < MAX_ANCHORS) {
            if (1 < 2) {
                Frame frame = arCam.getArSceneView().getArFrame();
                //int currentAnchorIndex = numberOfAnchors;
                int currentAnchorIndex = 1;
                Session session = arCam.getArSceneView().getSession();
                Anchor newMarkAnchor = session.createAnchor(
                        frame.getCamera().getPose()
                                .compose(Pose.makeTranslation(0, 0, -1f))
                                .extractTranslation());
                AnchorNode addedAnchorNode = new AnchorNode(newMarkAnchor);
                addedAnchorNode.setRenderable(gato1);
                //addAnchorNode(addedAnchorNode);
                currentSelectedAnchorNode = addedAnchorNode;
            } else {
                //Log.d(TAG,"MAX_ANCHORS exceeded");
            }
        }

    }*/

}


/*



 */