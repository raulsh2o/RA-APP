//package com.wheic.arapp.actividades;
package com.wheic.arapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import android.media.MediaPlayer;
public class SalvajesA extends AppCompatActivity {

    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    //****************************************************************
    private List<AnchorNode> anchorNodeList = new ArrayList<>();
    private AnchorNode currentSelectedAnchorNode = null;
    MediaPlayer audioMonkey, audioLion, audioTiger, audioElephant, audioSerpent,audioJirafa, audioHipopo, audioCocodrile;
    MediaPlayer audiogMonkey, audiogLion, audiogTiger, audiogElephant, audiogSerpent,audiogJirafa, audiogHipopo, audiogCocodrile;
    // (Money-), (Lion), (Tiger), (Elephant), Serpent, (Jira-), Hipop*-, (Crocodr-)
    private ModelRenderable monkey1, lion1, tiger1, elephant1, giraffe1, hipopo1, crocodrile1;//iguana2;
    private ModelRenderable serpiente01;
    private ModelRenderable jirafa01;
    private int Status1 = 0;
    private String information = "";
    //****************************************************************
    public static boolean checkSystemSupport(Activity activity) {

        //checking whether the API version of the running Android >= 24 that means Android Nougat 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            //checking whether the OpenGL version >= 3.0
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }

    }
// https://www.geeksforgeeks.org/how-to-build-a-simple-augmented-reality-android-app/
    // https://blog.ldtalentwork.com/2021/05/06/how-to-create-a-google-ar-core-sceneform-animation-in-android/
    //********************************************************************
    // (Money), (Lion), (Tiger), (Elephant), Serpent, (Jira), Hipop*, (Crocodr)
    //********************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        //arCam.getArSceneView().getScene().setOnPeekTouchListener(this::handleOnTouch);
        audioMonkey = MediaPlayer.create(this,R.raw.samono);
        audioLion = MediaPlayer.create(this,R.raw.saleon);
        audioTiger = MediaPlayer.create(this,R.raw.satigre);
        audioElephant = MediaPlayer.create(this,R.raw.saelefante);
        audioSerpent = MediaPlayer.create(this,R.raw.saserpiente);
        audioJirafa = MediaPlayer.create(this,R.raw.sajirafa);
        audioHipopo = MediaPlayer.create(this,R.raw.sahipopotamo);
        audioCocodrile = MediaPlayer.create(this,R.raw.sacocodrilo);
        //audioo = MediaPlayer.create(this,R.raw.vaudioo);
        //audiou = MediaPlayer.create(this,R.raw.vaudiou);

        //audiogMonkey = MediaPlayer.create(this,R.raw.samono);
        audiogLion = MediaPlayer.create(this,R.raw.sgalion);
        audiogTiger = MediaPlayer.create(this,R.raw.sgatiger);
        audiogElephant = MediaPlayer.create(this,R.raw.sgaelephant);
        audiogSerpent = MediaPlayer.create(this,R.raw.sgasnake);
        audioJirafa = MediaPlayer.create(this,R.raw.sajirafa);
        audioHipopo = MediaPlayer.create(this,R.raw.sahipopotamo);
        audioCocodrile = MediaPlayer.create(this,R.raw.sacocodrilo);

        //*************************************************************************
        ModelRenderable.builder()
                .setSource(this, R.raw.serpiente1)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> serpiente01=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.smonkey01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> monkey1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.sleon01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> lion1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.stigre01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> tiger1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.selephant01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> elephant1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.sgiraffe01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> giraffe1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.shipopotamo01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> hipopo1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.scocodrilo01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> crocodrile1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        //*************************************************************************

        if (checkSystemSupport(this)) {
            //*****************************************************
            /*String file ="checkSystemSupport";
            Toast toast = Toast.makeText(this, file, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();*/
            //****************************************************
            arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
            //ArFragment is linked up with its respective id used in the activity_main.xml
            arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                clickNo++;
                //the 3d model comes to the scene only when clickNo is one that means once
                //****************************************************
                //arCam.getArSceneView().getScene().removeChild(markAnchorNodeToMove);// test for remove
                //*****************************************************
                /*String file10 ="click Enter";
                Toast toast10 = Toast.makeText(this, file10, Toast.LENGTH_SHORT);
                toast10.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast10.show();*/
                //****************************************************
                if (clickNo == 2) {
                    //****************************************************
                    /*String file1 ="clickN1";
                    Toast toast1 = Toast.makeText(this, file1, Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast1.show();*/

                    //****************************************************
                    //.setSource(this, R.raw.gfg_gold_text_stand_24g)
                    // .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                    /*
                    Anchor anchor = hitResult.createAnchor();
                    ModelRenderable.builder()
                            .setSource(this, R.raw.gfg_gold_text_stand_24g)
                            .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                            .build()
                            .thenAccept(renderable -> gato1=renderable)
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                return null;
                            });
                    */
                   // arCam.getArSceneView().getScene().setOnPeekTouchListener(this::handleOnTouch);
                }
                if (clickNo == 1) {
                   //***********************************************************
                   /* String file2 ="clickN2";
                    Toast toast2 = Toast.makeText(this, file2, Toast.LENGTH_SHORT);
                    toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast2.show();*/
                    //************************************************************
                    removeAnchorNode(currentSelectedAnchorNode);
                    //************************************************************
                    //.setSource(this, R.raw.gfg_gold_text_stand_24g)
                    Anchor anchor = hitResult.createAnchor();
                    ModelRenderable.builder()
                            .setSource(this, R.raw.punto)
                            .setIsFilamentGltf(true)
                            .build()
                            .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Somthing is not right" + throwable.getMessage()).show();
                                return null;
                            });
                }
            });
        } else {
            return;
        }
        //***********************************************************************
        //FloatingActionButton gato1show = findViewById(R.id.id_gato1);
        Button mono1show = (Button) findViewById(R.id.id_Mono1);
        Button leon1show = (Button) findViewById(R.id.id_leon1);
        Button tigre1show = (Button) findViewById(R.id.id_tigre1);
        Button elefante1show = (Button) findViewById(R.id.id_elefante1);
        Button serpiente1show = (Button) findViewById(R.id.id_serpiente1);
        Button jirafa1show = (Button) findViewById(R.id.id_jirafa1);
        Button hipopotamo1show = (Button) findViewById(R.id.id_hipopotamo1);
        Button cocodrilo1show = (Button) findViewById(R.id.id_cocodrilo1);
        Button informacion = (Button) findViewById(R.id.id_informacion);
/*
        String file11 ="Serpiente";
        Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
        String file12 ="Jirafa";
        Toast toast12 = Toast.makeText(this, file12, Toast.LENGTH_SHORT);
*/
        informacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Status1 = 1;
                /*FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta");
                */

              /*  builder.setMessage("Mensaje de Animal")
                        .setTitle("Información")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });*/
                AlertDialog.Builder builder = new AlertDialog.Builder(SalvajesA.this);
                builder.setIcon(R.mipmap.ic_launcher).
                        setMessage(information).
                        setTitle("Información:");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        mono1show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //************************************************************
                Status1 = 1;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });
        leon1show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //************************************************************
                Status1 = 2;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });

        tigre1show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //************************************************************
                Status1 = 3;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });
        elefante1show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 4;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });

        serpiente1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //***********************************************************
                //String file11 ="click Gato 1";
                //Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
                /*toast11.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast11.show();*/
                //************************************************************
                Status1 = 5;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });

        jirafa1show.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 6;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });
        hipopotamo1show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 7;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });

        cocodrilo1show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 8;
                if (currentSelectedAnchorNode != null) {
                    //Get the current Pose and transform it then set a new anchor at the new pose
                    Session session = arCam.getArSceneView().getSession();
                    Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
                    Pose oldPose = currentAnchor.getPose();
                    Pose newPose = oldPose.compose(Pose.makeTranslation(0,0.05f,0));
                    currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);
                }
            }
        });
        //************************************************************************
    }
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
/*      Session session = arCam.getArSceneView().getSession();
        Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
        Pose oldPose = currentAnchor.getPose();
        Pose newPose = oldPose.compose(Pose.makeTranslation(0,0,0.05f));
        currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);*/
    }

    private void removeAnchorNode(AnchorNode nodeToremove) {
        //*********************************************************************
        /*String file11 ="remove Anchor Node";
        Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
        toast11.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast11.show();*/
        //*********************************************************************
        //Remove an anchor node
        if (nodeToremove != null) {
            //*********************************************************************
            /*String file12 ="nodeToremove != null";
            Toast toast12 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
            toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast12.show();*/
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
    }

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
                newMarkAnchorNode.setRenderable(monkey1);
                audioMonkey.start();
                information = "¿Sabías que los monos tienen un 96% de similitud genética con los seres humanos? Por esto mismo es que existen tantos casos de bebés chimpancés criados por seres humanos que adoptan a sus madres y repiten acciones como si fuesen humanos.";
            break;
            case 2:
                newMarkAnchorNode.setRenderable(lion1);
                audiogLion.start();
                information = "Los leones son sociables y suelen vivir en manadas. Como todos los felinos, los leones son cuadrúpedos y mamíferos. Poseen una cola larga, un pelaje corto y amarillento, así como una melena rubia u oscura alrededor del cuello en el caso de los machos.";
                break;
            case 3:
                newMarkAnchorNode.setRenderable(tiger1);
                audiogTiger.start();
                information = "Los tigres son los félidos más grandes del mundo. Aunque existen varias subespecies de tigres, la mayoría posee un hermoso pelaje de color anaranjado, el vientre blanco, y múltiples rayas marrones o negras por todo el cuerpo. Estas rayas les ayudan a camuflarse entre la vegetación y son distintas en cada ejemplar.";
                break;
            case 4:
                newMarkAnchorNode.setRenderable(elephant1);
                audiogElephant.start();
                information = "Son animales mamíferos, exclusivamente herbívoros cuya característica principal –además del gran tamaño– es su larga trompa y sus enormes orejas. Se organizan en manadas de unos 20 miembros, miden casi 4 metros los ejemplares más grandes y pueden llegar a pesar más de 6.000 kilos";
                break;
            case 5:
                newMarkAnchorNode.setRenderable(serpiente01);
                audiogSerpent.start();
                information = "\n" +
                        "Las serpientes son unos reptiles que carecen de patas y, sin embargo, se desplazan con facilidad. Poseen un cuerpo muy delgado, pero pueden comer animales mucho más grandes que ellas. Además, algunas especies presentan un veneno tan potente que puede matar a un ser humano.";
                break;
            case 6:
                newMarkAnchorNode.setRenderable(giraffe1);
                audioJirafa.start();
                information = "La jirafa es el animal terrestre más alto del mundo y uno de los protagonistas indiscutibles de la sabana africana. Esta peculiar especie puede llegar a medir 5 metros de altura.";
                break;

            case 7:
                newMarkAnchorNode.setRenderable(hipopo1);
                audioHipopo.start();
                information = "Los hipopótamos son animales voluminosos, cuadrúpedos, con cuerpo rechoncho, en forma de barril, y una cabeza grande de boca ancha y morro grueso. Poseen una piel lisa y sin pelo.";
                break;
            case 8:
                newMarkAnchorNode.setRenderable(crocodrile1);
                audioCocodrile.start();
                information = "Los cocodrilos son reptiles, lo que significa que son de sangre fría, ellos están cubiertos de piel seca y escamosa y tienen una mandíbula y dientes capaces de aplastar huesos.";
                break;
            /*case 20:
                newMarkAnchorNode.setRenderable(jirafa01);
            break;*/

            default:
            break;
        }

        newMarkAnchorNode.setParent(arCam.getArSceneView().getScene());
        anchorNodeList.add(newMarkAnchorNode);

        //Delete the line if it is drawn
        //removeLine(nodeForLine);

        return newMarkAnchorNode;
    }
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


    public class DialogoAlerta extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());

            builder.setMessage("Mensaje de Animal")
                    .setTitle("Información")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            return builder.create();
        }
    }

}

