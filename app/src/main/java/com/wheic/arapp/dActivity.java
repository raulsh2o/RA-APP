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


public class dActivity extends AppCompatActivity {

    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    //****************************************************************
    MediaPlayer audioDog, audioCat, audioRabbit, audioChicken, audioHorse,audioBird, audioDuck, audioCow, audioi, audiogDog, audiogCat, audiogRabbit, audiogChicken, audiogHorse,audiogBird, audiogDuck, audiogCow;
    //****************************************************************
    private List<AnchorNode> anchorNodeList = new ArrayList<>();
    private AnchorNode currentSelectedAnchorNode = null;
    private ModelRenderable gato1,dog1,rabbit1,chicken1,horse1,bird1,duck1,cow1;


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
    //********************************************************************
    // (dog), cat, (rabbit), (gallina), (caballo),(pajaro), (pato), (vaca)
    //********************************************************************
// https://www.geeksforgeeks.org/how-to-build-a-simple-augmented-reality-android-app/
    // https://blog.ldtalentwork.com/2021/05/06/how-to-create-a-google-ar-core-sceneform-animation-in-android/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        //arCam.getArSceneView().getScene().setOnPeekTouchListener(this::handleOnTouch);
        audioDog = MediaPlayer.create(this,R.raw.daperro);
        audioCat = MediaPlayer.create(this,R.raw.dagato);
        audioRabbit = MediaPlayer.create(this,R.raw.daconejo);
        audioChicken = MediaPlayer.create(this,R.raw.dagallina);
        audioHorse = MediaPlayer.create(this,R.raw.dacaballo);
        audioBird = MediaPlayer.create(this,R.raw.dapajaro);
        audioDuck = MediaPlayer.create(this,R.raw.dapato);
        audioCow = MediaPlayer.create(this,R.raw.davaca);
        audioi = MediaPlayer.create(this,R.raw.naintro);
        //RUGIDOS
        audiogDog = MediaPlayer.create(this,R.raw.sgperro);
        audiogCat = MediaPlayer.create(this,R.raw.sggato);
        audiogRabbit = MediaPlayer.create(this,R.raw.sgconejo);
        audiogChicken = MediaPlayer.create(this,R.raw.sggallina);
        audiogHorse = MediaPlayer.create(this,R.raw.sgcaballo);
        audiogBird = MediaPlayer.create(this,R.raw.sgpajaro);
        audiogDuck = MediaPlayer.create(this,R.raw.sgpato);
        audiogCow = MediaPlayer.create(this,R.raw.sgvaca);
        //REPRODUCIR AUDIO DE INTRO
        audioi.start();
        choose = "audioi";

        //*************************************************************************
        ModelRenderable.builder()
                .setSource(this, R.raw.gfg_gold_text_stand_24g)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> gato1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right gato1" + throwable.getMessage()).show();
                    return null;
                });



        /*
        ModelRenderable.builder()
                .setSource(this, R.raw.iguana02)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> iguana2=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
*/
        ModelRenderable.builder()
                .setSource(this, R.raw.ddog01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> dog1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.dhorse01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> horse1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        //dog1,rabbit1,chicken1,horse1,bird1,duck1,cow1;
        ModelRenderable.builder()
                .setSource(this, R.raw.drabbit01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> rabbit1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.dchicken01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> chicken1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.dbird01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> bird1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.dduck01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> duck1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.dcow01)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> cow1=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });

       /* ModelRenderable.builder()
                .setSource(this, R.raw.iguana02)
                .setIsFilamentGltf(true) //maybe is possible remove for other extension 3D
                .build()
                .thenAccept(renderable -> iguana2=renderable)
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Something is not right iguana2" + throwable.getMessage()).show();
                    return null;
                });*/
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
        Button gato1show = (Button) findViewById(R.id.id_gato1);
        //Button iguana2show = (Button) findViewById(R.id.id_iguana2);
        Button pajaro1show = (Button) findViewById(R.id.id_pajaro1);
        Button gallina1show= (Button) findViewById(R.id.id_gallina1);
        Button vaca1show=(Button) findViewById(R.id.id_vaca1);
        Button perro1show=(Button) findViewById(R.id.id_perro1);
        Button pato1show=(Button) findViewById(R.id.id_pato1);
        Button caballo1show=(Button) findViewById(R.id.id_caballo1);
        Button conejo1show=(Button) findViewById(R.id.id_conejo1);
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
                if (choose == "gato"){
                    stopSound(choose);
                    audioCat.start();
                    choose = "ggato";
                }else if (choose == "pajaro"){
                    stopSound(choose);
                    audioBird.start();
                    choose = "gpajaro";
                }else if (choose == "gallina"){
                    stopSound(choose);
                    audioChicken.start();
                    choose = "ggallina";
                }else if (choose == "vaca"){
                    stopSound(choose);
                    audioCow.start();
                    choose = "gvaca";
                }else if (choose == "perro"){
                    stopSound(choose);
                    audioDog.start();
                    choose = "gperro";
                }else if (choose == "pato"){
                    stopSound(choose);
                    audioDuck.start();
                    choose = "gpato";
                }else if (choose == "caballo"){
                    stopSound(choose);
                    audioHorse.start();
                    choose = "gcaballo";
                }else if (choose == "conejo"){
                    stopSound(choose);
                    audioRabbit.start();
                    choose = "gconejo";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(dActivity.this);
                builder.setIcon(R.drawable.info).
                        setMessage(information).
                        setTitle("Informaci??n:");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        gato1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //***********************************************************
                //String file11 ="click Gato 1";
                //Toast toast11 = Toast.makeText(this, file11, Toast.LENGTH_SHORT);
                /*toast11.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
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


                    /*
                    TransformableNode andy = new TransformableNode(arCam.getTransformationSystem());
                    andy.setParent(currentSelectedAnchorNode);
                    andy.setRenderable(rabbit1);
                    andy.select();
                    */




                }
            }
        });

        pajaro1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        gallina1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        vaca1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        perro1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        pato1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        caballo1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        conejo1show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        /*iguana2show.setOnClickListener(new View.OnClickListener(){
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
        });*/

        //************************************************************************
    }
    private <AnimationData> void addModel(Anchor anchor, ModelRenderable modelRenderable) {
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
        //**************************************************
        /*// Get the animation data called "andy_dance" from the `andyRenderable`.
        AnimationData danceData = modelRenderable.getAnimationData("vocal_a");
        // Get the animation name.
        danceData.getName();
        // Access animations by index.
        numAnimations = model.getAnimationDataCount();
        danceData = gato1.getAnimationData(0);

        ModelAnimator andyAnimator = new ModelAnimator(danceData, andyRenderable);
        gato1.start();
        gato1.setRepeatCount(<number of repeats>);*/
        //*******************************************************
        //*********************************************************
       // filamentAsset = model.getRenderableInstance().getFilamentAsset();
        //**********************************************************
/*      Session session = arCam.getArSceneView().getSession();
        Anchor currentAnchor = currentSelectedAnchorNode.getAnchor();
        Pose oldPose = currentAnchor.getPose();
        Pose newPose = oldPose.compose(Pose.makeTranslation(0,0,0.05f));
        currentSelectedAnchorNode = moveRenderable(currentSelectedAnchorNode, newPose);*/
    }

    private void removeAnchorNode(AnchorNode nodeToremove) {
        //*********************************************************************
       /* String file11 ="remove Anchor Node";
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

    private void stopSound(String sound){
        if (sound == "gato"){
            audiogCat.pause();
        }else if (sound == "pajaro"){
            audiogBird.pause();
        }else if (sound == "gallina"){
            audiogChicken.pause();
        }else if (sound == "vaca"){
            audiogCow.pause();
        }else if (sound == "perro"){
            audiogDog.pause();
        }else if (sound == "pato"){
            audiogDuck.pause();
        }else if (sound == "caballo"){
            audiogHorse.pause();
        }else if (sound == "conejo"){
            audiogRabbit.pause();
        }else if (sound == "ggato"){
            audioCat.pause();
        }else if (sound == "gpajaro"){
            audioBird.pause();
        }else if (sound == "ggallina"){
            audioChicken.pause();
        }else if (sound == "gvaca"){
            audioCow.pause();
        }else if (sound == "gperro"){
            audioDog.pause();
        }else if (sound == "gpato"){
            audioDuck.pause();
        }else if (sound == "gcaballo"){
            audioHorse.pause();
        }else if (sound == "gconejo"){
            audioRabbit.pause();
        }
    }

    private AnchorNode moveRenderable(AnchorNode markAnchorNodeToMove, Pose newPoseToMoveTo) {
        //Move a renderable to a new pose
        if (markAnchorNodeToMove != null) {
            arCam.getArSceneView().getScene().removeChild(markAnchorNodeToMove);
            anchorNodeList.remove(markAnchorNodeToMove);
        } else {
            //Log.d(TAG,"moveRenderable - markAnchorNode was null, the little ??$%^...");
            return null;
        }

        Frame frame = arCam.getArSceneView().getArFrame();
        Session session = arCam.getArSceneView().getSession();
        Anchor markAnchor = session.createAnchor(newPoseToMoveTo.extractTranslation());
        AnchorNode newMarkAnchorNode = new AnchorNode(markAnchor);
        TransformableNode andy = new TransformableNode(arCam.getTransformationSystem());
        andy.setParent(newMarkAnchorNode);

        switch(Status1)
        {
            case 1:

                stopSound(choose);
                choose = "gato";
                andy.setRenderable(gato1);
                audiogCat.start();
                information = "Mam??fero de contextura peque??a, de abundante pelaje y muy suave, son muy cari??oso con los humanos.";

            break;

            case 2:
                //newMarkAnchorNode.setRenderable(iguana2);
                stopSound(choose);
                choose = "pajaro";
                andy.setRenderable(bird1);
                audiogBird.start();
                information = "Las aves son seres extraordinarios y fascinantes: muchas de ellas poseen un plumaje colorido, producen sonidos extraordinarios o pueden volar.";
            break;
            case 3:
                stopSound(choose);
                choose = "gallina";
                andy.setRenderable(chicken1);
                audiogChicken.start();
                information = "La gallina es denominado un ave conocida por su cacareo, pone huevos, y est?? cubierta de plumas de diversos colores";
                break;
            case 4:
                stopSound(choose);
                choose = "vaca";
                andy.setRenderable(cow1);
                audiogCow.start();
                information = "La vaca es un animal mam??fero, se alimenta del pasto, hierbas, tallos, hojas, semillas y ra??ces.";
                break;
            case 5:
                stopSound(choose);
                choose = "perro";
                andy.setRenderable(dog1);
                audiogDog.start();
                information = "El perro dom??stico es un mam??fero carn??voro, Su tama??o, forma y pelaje var??an en funci??n de la raza de perro, ven bien, usan mayormente su o??do y su olfato, sentidos que tienen muy desarrollados y que son muy pr??cticos para el humano.";
                break;
            case 6:
                stopSound(choose);
                choose = "pato";
                andy.setRenderable(duck1);
                audiogDuck.start();
                information = "El pato es un ave, vive cerca del agua y nadan.";
                break;
            case 7:
                stopSound(choose);
                choose = "caballo";
                andy.setRenderable(horse1);
                audiogHorse.start();
                information = "Un Caballo es un animal cuadr??pedo perteneciente a la especie de los mam??feros, se caracteriza por su gran tama??o, son animales que galopan y relinchan";
                break;
            case 8:
                stopSound(choose);
                choose = "conejo";
                andy.setRenderable(rabbit1);
                audiogRabbit.start();
                information = "Son animales que tienen muy buena relaci??n con los humanos, ya que son muy amistosos y agradables.";
                break;
            default:
            break;
        }
        newMarkAnchorNode.setParent(arCam.getArSceneView().getScene());
        anchorNodeList.add(newMarkAnchorNode);
        //*****************************************************
        // Get the animation data called "andy_dance" from the `andyRenderable`.

        //******************************************************
        //Delete the line if it is drawn
        //removeLine(nodeForLine);

        return newMarkAnchorNode;
    }

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

    }

}