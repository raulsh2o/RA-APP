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


public class FrutasA extends AppCompatActivity {
    MediaPlayer audiomanzana, audiopera, audiobanana, audiouva, audiosandia, audiomelon, audiopina, audioi;
    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    //****************************************************************
    private List<AnchorNode> anchorNodeList = new ArrayList<>();
    private AnchorNode currentSelectedAnchorNode = null;

    private ModelRenderable pera, manzana, banana,uva,sandia,melon,pina;
    private int Status1 = 0;
    private String information = "";
    private String choose = "";


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
    //apple, pera,uva, banana,sandia,melon, piña
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f);
        //arCam.getArSceneView().getScene().setOnPeekTouchListener(this::handleOnTouch);
        audiomanzana = MediaPlayer.create(this,R.raw.famanzana);
        audiopera = MediaPlayer.create(this,R.raw.fapera);
        audiobanana = MediaPlayer.create(this,R.raw.fabanana);
        audiouva = MediaPlayer.create(this,R.raw.fauva);
        audiosandia = MediaPlayer.create(this,R.raw.fasandia);
        audiomelon = MediaPlayer.create(this,R.raw.famelon);
        audiopina = MediaPlayer.create(this,R.raw.fapina);
        audioi = MediaPlayer.create(this,R.raw.naintro);

        //REPRODUCIR AUDIO DE INTRO
        audioi.start();
        choose = "audioi";
        //*************************************************************************
        ModelRenderable.builder()
                .setSource(this, R.raw.fpera)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> pera=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.fmanzana)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> manzana=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.fbanana)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> banana=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.fuva)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> uva=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.fsandia)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> sandia=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.fmelon)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> melon=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });

        ModelRenderable.builder()
                .setSource(this, R.raw.fpina)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> pina=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });
        /*
        ModelRenderable.builder()
                .setSource(this, R.raw.vocal_i)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> vocali=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.vocal_o)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> vocalo=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.vocal_u)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> vocalu=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });*/
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
               /* String file10 ="click Enter";
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
                    /*String file2 ="clickN2";
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
        Button Pera = (Button) findViewById(R.id.id_Pera);
        Button Manzana = (Button) findViewById(R.id.id_Manzana);
        Button Banana = (Button) findViewById(R.id.id_Banana);
        Button Uva = (Button) findViewById(R.id.id_Uva);
        Button Sandia = (Button) findViewById(R.id.id_Sandia);
        Button Melon = (Button) findViewById(R.id.id_Melon);
        Button Pina = (Button) findViewById(R.id.id_Piña);
        ImageButton informacion = (ImageButton) findViewById(R.id.id_informacion);
        /*Button Vocali = (Button) findViewById(R.id.id_vocali);
        Button Vocalo = (Button) findViewById(R.id.id_vocalo);
        Button Vocalu = (Button) findViewById(R.id.id_vocalu);*/
/*
        String file11 ="click Gato 1";
        Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
        String file12 ="click Iguana 2";
        Toast toast12 = Toast.makeText(this, file12, Toast.LENGTH_SHORT);
*/
        informacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (choose == "pera"){
                    //stopSound(choose);
                    audiopera.start();
                }else if (choose == "manzana"){
                    //stopSound(choose);
                    audiomanzana.start();
                }else if (choose == "banana"){
                    //stopSound(choose);
                    audiobanana.start();
                }else if (choose == "uva"){
                    //stopSound(choose);
                    audiouva.start();
                }else if (choose == "sandia"){
                    //stopSound(choose);
                    audiosandia.start();
                }else if (choose == "melon"){
                    //stopSound(choose);
                    audiomelon.start();
                }else if (choose == "pina"){
                    //stopSound(choose);
                    audiopina.start();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(FrutasA.this);
                builder.setIcon(R.drawable.info).
                        setMessage(information).
                        setTitle("Información:");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        Pera.setOnClickListener(new View.OnClickListener() {
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

        Manzana.setOnClickListener(new View.OnClickListener(){
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

        Banana.setOnClickListener(new View.OnClickListener(){
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

        Uva.setOnClickListener(new View.OnClickListener(){
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

        Sandia.setOnClickListener(new View.OnClickListener(){
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

        Melon.setOnClickListener(new View.OnClickListener(){
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

        Pina.setOnClickListener(new View.OnClickListener(){
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
/*
        Vocali.setOnClickListener(new View.OnClickListener(){
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
        Vocalo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

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
        Vocalu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

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
        */
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

    private void stopSound(String sound){
        if (sound == "pera"){
            audiopera.pause();
        }else if (sound == "manzana"){
            audiomanzana.pause();
        }else if (sound == "banana"){
            audiobanana.pause();
        }else if (sound == "uva"){
            audiouva.pause();
        }else if (sound == "sandia"){
            audiosandia.pause();
        }else if (sound == "melon"){
            audiomelon.pause();
        }else if (sound == "pina"){
            audiopina.pause();
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
                stopSound(choose);
                choose = "pera";
                newMarkAnchorNode.setRenderable(pera);
                //audiopera.start();
                information = "La pera tiene forma como una guitarra, es rica y da mucha energía.";
            break;

            case 2:
                stopSound(choose);
                choose = "manzana";
                newMarkAnchorNode.setRenderable(manzana);
                //audiomanzana.start();
                information = "La manzana es roja, amarilla, verde, es dulces , una fruta muy rica para hacernos fuerte ( puedes ayudar con imágenes, sería ideal)";
            break;
            case 3:
                stopSound(choose);
                choose = "banana";
                newMarkAnchorNode.setRenderable(banana);
                //audiobanana.start();
                information = "La banana es de color amarillo por fuera pero blanco por dentro, es muy dulce y muy rico en vitaminas.";
                break;
            case 4:
                stopSound(choose);
                choose = "uva";
                newMarkAnchorNode.setRenderable(uva);
                //audiouva.start();
                information = "La uva tiene forma de pequeños círculos, hay una semillita en el centro, tiene colores verde y morado, es dulce y muy rica.";
                break;
            case 5:
                stopSound(choose);
                choose = "sandia";
                newMarkAnchorNode.setRenderable(sandia);
                //audiosandia.start();
                information = "La sandía es una fruta verde por fuera y roja por dentro, tiene semillitas las cuales no hay que comer, es muy jugosa, rica y dulce.";
                break;
            case 6:
                stopSound(choose);
                choose = "melon";
                newMarkAnchorNode.setRenderable(melon);
                //audiomelon.start();
                information = "El melón es una fruta muy dulce, pero para probarla, hay que pelar, hay de varios colores, tiene semillita, pero no se pueden comer.";
                break;
            case 7:
                stopSound(choose);
                choose = "pina";
                newMarkAnchorNode.setRenderable(pina);
                //audiopina.start();
                information = "La piña es una fruta que tiene una forma muy particular, es grande y por dentro es muy dulces y huele muy rico, sirve también para jugos.";
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