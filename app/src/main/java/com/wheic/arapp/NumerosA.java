//package com.wheic.arapp.actividades;
package com.wheic.arapp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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



public class NumerosA extends AppCompatActivity {
    MediaPlayer audio0, audio1, audio2, audio3, audio4,audio5,audio6,audio7,audio8,audio9,audio10,audioi;
    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    //****************************************************************
    private List<AnchorNode> anchorNodeList = new ArrayList<>();
    private AnchorNode currentSelectedAnchorNode = null;

    private ModelRenderable cero,uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,diez;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n);
        //arCam.getArSceneView().getScene().setOnPeekTouchListener(this::handleOnTouch);
        audio0 = MediaPlayer.create(this,R.raw.nacero);
        audio1 = MediaPlayer.create(this,R.raw.nauno);
        audio2 = MediaPlayer.create(this,R.raw.nados);
        audio3 = MediaPlayer.create(this,R.raw.natres);
        audio4 = MediaPlayer.create(this,R.raw.nacuatro);
        audio5 = MediaPlayer.create(this,R.raw.nacinco);
        audio6 = MediaPlayer.create(this,R.raw.naseis);
        audio7 = MediaPlayer.create(this,R.raw.nasiete);
        audio8 = MediaPlayer.create(this,R.raw.naocho);
        audio9 = MediaPlayer.create(this,R.raw.nanueve);
        audio10 = MediaPlayer.create(this,R.raw.nadiez);
        audioi = MediaPlayer.create(this,R.raw.naintro);

        //REPRODUCIR AUDIO DE INTRO
        audioi.start();
        //*************************************************************************
        ModelRenderable.builder()
                .setSource(this, R.raw.n0)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> cero=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n1)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> uno=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n2)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> dos=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n3)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> tres=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n4)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> cuatro=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n5)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> cinco=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n6)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> seis=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n7)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> siete=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n8)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> ocho=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n9)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> nueve=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.n10)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> diez=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        //*************************************************************************

        if (checkSystemSupport(this)) {
            //*****************************************************
           /* String file ="checkSystemSupport";
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

                //****************************************************
                if (clickNo == 2) {

                }
                if (clickNo == 1) {
                   //***********************************************************
                    audioi.start();
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
        Button Ncero = (Button) findViewById(R.id.id_cero);
        Button Nuno = (Button) findViewById(R.id.id_uno);
        Button Ndos = (Button) findViewById(R.id.id_dos);
        Button Ntres = (Button) findViewById(R.id.id_tres);
        Button Ncuatro = (Button) findViewById(R.id.id_cuatro);
        Button Ncinco = (Button) findViewById(R.id.id_cinco);
        Button Nseis = (Button) findViewById(R.id.id_seis);
        Button Nsiete = (Button) findViewById(R.id.id_siete);
        Button Nocho = (Button) findViewById(R.id.id_ocho);
        Button Nnueve = (Button) findViewById(R.id.id_nueve);
        Button Ndiez = (Button) findViewById(R.id.id_diez);
        ImageButton informacion = (ImageButton) findViewById(R.id.id_informacion);
/*
        String file11 ="click Gato 1";
        Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
        String file12 ="click Iguana 2";
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
                AlertDialog.Builder builder = new AlertDialog.Builder(NumerosA.this);
                builder.setIcon(R.drawable.info).
                        setMessage(information).
                        setTitle("Información:");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        Ncero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //***********************************************************
                //String file11 ="click Gato 1";
                //Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
               /* toast11.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast11.show();*/
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

        Nuno.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
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

        Ndos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
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
        Ntres.setOnClickListener(new View.OnClickListener(){
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
        Ncuatro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
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
        Ncinco.setOnClickListener(new View.OnClickListener(){
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

        Nseis.setOnClickListener(new View.OnClickListener(){
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

        Nsiete.setOnClickListener(new View.OnClickListener(){
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
        Nocho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 9;
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
        Nnueve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 10;
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
        Ndiez.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*toast12.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast12.show();*/
                //************************************************************
                Status1 = 11;
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
           /* String file12 ="nodeToremove != null";
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
                newMarkAnchorNode.setRenderable(cero);
                audio0.start();
                information = "El número cero es la rueda moscovita para pasarlo bien.";
            break;

            case 2:
                newMarkAnchorNode.setRenderable(uno);
                audio1.start();
                information = "El número uno es un soldado haciendo la instrucción.";
            break;
            case 3:
                newMarkAnchorNode.setRenderable(dos);
                audio2.start();
                information = "El número dos es un patito que está tomando el sol.";
                break;
            case 4:
                newMarkAnchorNode.setRenderable(tres);
                audio3.start();
                information = "El número tres es una serpiente que baila sin parar.";
                break;
            case 5:
                newMarkAnchorNode.setRenderable(cuatro);
                audio4.start();
                information = "El número cuatro es una sillita que invita a descansar.";
                break;
            case 6:
                newMarkAnchorNode.setRenderable(cinco);
                audio5.start();
                information = "El número cinco tiene orejas, parece un conejito.";
                break;

            case 7:
                newMarkAnchorNode.setRenderable(seis);
                audio6.start();
                information = "El número seis es una pera redonda y con rabito.";
                break;
            case 8:
                newMarkAnchorNode.setRenderable(siete);
                audio7.start();
                information = "El número siete es un sereno con gorra y con bastón.";
                break;
            case 9:
                newMarkAnchorNode.setRenderable(ocho);
                audio8.start();
                information = "El número ocho son las gafas que lleva don Ramón.";
                break;
            case 10:
                newMarkAnchorNode.setRenderable(nueve);
                audio9.start();
                information = "El número nueve es un globito atado a un cordel.";
                break;

            case 11:
                newMarkAnchorNode.setRenderable(diez);
                audio10.start();
                information = "El número diez es un soldado que lleva un gran melón.";
                break;

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

}