package com.example.sih2019psit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Name,Pob,Prn,Ipd,Age,Address,Village,District,Diagnosis,Postal,Constitution;
    RadioGroup Sex;
    RadioButton mf;
    String whichSex;

    EditText Date,Time,Description,Specify;
    String []arr=new String[5];

    EditText Addictions,PreviousAllergies;

    EditText NameOfDrug,BatchNo,Dose,RouteOfAdministration,DateOfStart,DateOfStop,ReasonForUse,AnyUnwantedOccurence;

    EditText NameOfDrug1,BatchNo1,Dose1,RouteOfAdministration1,DateOfStart1,DateOfStop1,ReasonForUse1,AnyUnwantedOccurence1;

    EditText NameOfSuspectedDrug,ManufactureOfDrug,ExpiryOfDrug,RemainingPack,ConsumedWith,DietryPrecaution,PrescriptionBased,RelevantInfo;

    EditText ManagementProvided,DobFatal,ReactionAbated,ReactionReappeared,PatientAdmittedHospitalAddress;
    RadioGroup OutCome,Severe;
    RadioButton SelectedOutCome,Condition;
    String RbSelectedOutcome,RbCondition;

    EditText AbnormalFindings,ReporterName,ReporterAddress,ReporterTelephone,ReporterEmail,ReporterVillage,ReporterPost,ReporterState;

    Button Submit;

    FirebaseFirestore db;
    CollectionReference cref;
    Button Signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.etName);
        Pob = (EditText) findViewById(R.id.etPOB);
        Prn = (EditText) findViewById((R.id.etPRN));
        Ipd = (EditText) findViewById(R.id.etIPD);
        Age = (EditText) findViewById(R.id.etAge);
        Address = (EditText) findViewById(R.id.etAddress);
        Village = (EditText) findViewById(R.id.etVillage);
        District = (EditText) findViewById(R.id.etDistrict);
        Diagnosis = (EditText) findViewById(R.id.etDiagnosis);
        Sex = (RadioGroup) findViewById(R.id.rgSex);
        Postal = (EditText) findViewById(R.id.etPost);
        Constitution = (EditText) findViewById(R.id.etConstitution);
        //Radio Group
        Sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mf = Sex.findViewById(checkedId);
                switch (checkedId) {
                    case R.id.rbMale:
                        whichSex = mf.getText().toString();
                        break;
                    case R.id.rbFemale:
                        whichSex = mf.getText().toString();
                        break;
                    default:

                }
            }
        });

        Date = (EditText) findViewById(R.id.etName);
        Time = (EditText) findViewById(R.id.etPOB);
        Description = (EditText) findViewById((R.id.etDescription));
        Specify = (EditText) findViewById(R.id.etSpecify);

        Addictions = (EditText) findViewById(R.id.etAddictions);
        PreviousAllergies = (EditText) findViewById(R.id.etPreviousAllergies);

        NameOfDrug = (EditText) findViewById(R.id.etNameOfDrug);
        BatchNo = (EditText) findViewById(R.id.etBatchNumber);
        Dose = (EditText) findViewById(R.id.etDose);
        RouteOfAdministration = (EditText) findViewById(R.id.etRouteOfAdministration);
        DateOfStart = (EditText) findViewById(R.id.etDateOfStarting);
        DateOfStop = (EditText) findViewById(R.id.etDateOfStopping);
        RouteOfAdministration = (EditText) findViewById(R.id.etReasonForUse);
        AnyUnwantedOccurence = (EditText) findViewById(R.id.etAnyUnwantedOccurrences);

        NameOfDrug1 = (EditText) findViewById(R.id.etNameOfDrug1);
        BatchNo1 = (EditText) findViewById(R.id.etBatchNumber1);
        Dose1 = (EditText) findViewById(R.id.etDose1);
        RouteOfAdministration1 = (EditText) findViewById(R.id.etRouteOfAdministration1);
        DateOfStart1 = (EditText) findViewById(R.id.etDateOfStarting1);
        DateOfStop1 = (EditText) findViewById(R.id.etDateOfStopping1);
        RouteOfAdministration1 = (EditText) findViewById(R.id.etReasonForUse1);
        AnyUnwantedOccurence1 = (EditText) findViewById(R.id.etAnyUnwantedOccurrences1);

        NameOfSuspectedDrug = (EditText) findViewById(R.id.etNameOfSuspectedDrug);
        ManufactureOfDrug = (EditText) findViewById(R.id.etBatchNumber1);
        ExpiryOfDrug = (EditText) findViewById(R.id.etDose1);
        RemainingPack = (EditText) findViewById(R.id.etRouteOfAdministration1);
        ConsumedWith = (EditText) findViewById(R.id.etDateOfStarting1);
        DietryPrecaution = (EditText) findViewById(R.id.etDateOfStopping1);
        PrescriptionBased = (EditText) findViewById(R.id.etReasonForUse1);
        RelevantInfo = (EditText) findViewById(R.id.etAnyUnwantedOccurrences1);

        ManagementProvided = (EditText) findViewById(R.id.etMgmtProvided);
        DobFatal = (EditText) findViewById(R.id.etDobFatal);
        ReactionAbated = (EditText) findViewById(R.id.etReactionAbated);
        ReactionReappeared = (EditText) findViewById(R.id.etReactionReappeared);
        PatientAdmittedHospitalAddress = (EditText) findViewById(R.id.etAdmittedHospital);

        OutCome = (RadioGroup) findViewById(R.id.rgSevere);
        OutCome = (RadioGroup) findViewById(R.id.rgOutCome);


        OutCome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SelectedOutCome = OutCome.findViewById(checkedId);
                switch (checkedId) {
                    case R.id.rbRecovered:
                        RbSelectedOutcome = SelectedOutCome.getText().toString();
                        break;
                    case R.id.rbNotRecovered:
                        RbSelectedOutcome = SelectedOutCome.getText().toString();
                        break;
                    case R.id.rbUnknown:
                        RbSelectedOutcome = SelectedOutCome.getText().toString();
                        break;
                    case R.id.rbFatal:
                        RbSelectedOutcome = SelectedOutCome.getText().toString();
                        break;

                    default:

                }
            }
        });
        if(Severe != null) {
            Severe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Condition = Severe.findViewById(checkedId);
                    switch (checkedId) {
                        case R.id.rbYes:
                            RbCondition = Condition.getText().toString();
                            break;
                        case R.id.rbNo:
                            RbCondition = Condition.getText().toString();
                            break;
                        default:

                    }
                }
            });
        }

        AbnormalFindings = (EditText) findViewById(R.id.etAbnormalFindings);
        ReporterName = (EditText) findViewById(R.id.etReporterName);
        ReporterAddress = (EditText) findViewById((R.id.etReporterAddress));
        ReporterTelephone = (EditText) findViewById(R.id.etReporterTelephone);
        ReporterEmail = (EditText) findViewById(R.id.etReporterEmail);
        ReporterVillage = (EditText) findViewById(R.id.etReporterVillage);
        ReporterPost = (EditText) findViewById(R.id.etReporterPost);
        ReporterState = (EditText) findViewById(R.id.etReporterState);


        Submit =(Button)findViewById(R.id.btnSubmit);










        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=FirebaseFirestore.getInstance();
                final Map<String,Object> data=new HashMap<>();
                data.put("patientName",Name.getText().toString());
                data.put("PRNnum",Prn.getText().toString());
                data.put("BirthPlace",Pob.getText().toString());
                data.put("IPDnum",Ipd.getText().toString());
                data.put("patientAddress",Address.getText().toString());
                data.put("postalAddress",Postal.getText().toString());
                data.put("patientAge",Age.getText().toString());
                data.put("patientSex",whichSex);
                data.put("Constitution",Constitution.getText().toString());
                data.put("Diagnosis",Diagnosis.getText().toString());

                data.put("DateOfReaction",Date.getText().toString());
                data.put("timeOfReaction",Time.getText().toString());
                data.put("descriptionOfReaction",Description.getText().toString());
                data.put("Mention",Specify.getText().toString());
                data.put("hepatic",arr[0]);
                data.put("rental",arr[1]);
                data.put("cardiac",arr[2]);
                data.put("diabetec",arr[3]);
                data.put("anyOther",arr[4]);



                data.put("addiction",Addictions.getText().toString());
                data.put("allergy",PreviousAllergies.getText().toString());

                data.put("nameOfDrug1",NameOfDrug.getText().toString());
                data.put("batchNumber1",BatchNo.getText().toString());
                data.put("doseDetails1",Dose.getText().toString());
                data.put("AdminRoute1",RouteOfAdministration.getText().toString());
                data.put("startDate1",DateOfStart.getText().toString());
                data.put("stopDate1",DateOfStop.getText().toString());
                data.put("reasonOfUse1",ReasonForUse.getText().toString());
                data.put("unwantedOccurrence1",AnyUnwantedOccurence.getText().toString());

                data.put("nameOfDrug7",NameOfDrug1.getText().toString());
                data.put("batchNumber7",BatchNo1.getText().toString());
                data.put("doseDetails7",Dose1.getText().toString());
                data.put("AdminRoute7",RouteOfAdministration1.getText().toString());
                data.put("startDate7",DateOfStart1.getText().toString());
                data.put("stopDate7",DateOfStop1.getText().toString());
                data.put("reasonOfUse7",ReasonForUse1.getText().toString());
                data.put("unwantedOccurrence7",AnyUnwantedOccurence1.getText().toString());

                data.put("nameOfSuspectedDrug",NameOfSuspectedDrug.getText().toString());
                data.put("manuOfDrug",ManufactureOfDrug.getText().toString());
                data.put("expiryOfDrug",ExpiryOfDrug.getText().toString());
                data.put("remainingPack",RemainingPack.getText().toString());
                data.put("consumedWith",ConsumedWith.getText().toString());
                data.put("dietryPrecaution",DietryPrecaution.getText().toString());
                data.put("prescriptionBased",PrescriptionBased.getText().toString());
                data.put("relevantInfo",RelevantInfo.getText().toString());


                data.put("adverseReaction",ManagementProvided.getText().toString());
                data.put("outcomesOfAdverseReaction",RbSelectedOutcome);
                data.put("dobIfFatal",DobFatal.getText().toString());
                data.put("serve",RbCondition);
                data.put("reactionAbated",ReactionAbated.getText().toString());
                data.put("reactionReappeared",ReactionReappeared.getText().toString());
                data.put("hospitalAddress",PatientAdmittedHospitalAddress.getText().toString());


                data.put("abnormalFindings",AbnormalFindings.getText().toString());
                data.put("filerName",ReporterName.getText().toString());
                data.put("filerAddress",ReporterAddress.getText().toString());
                data.put("filerContactNo",ReporterTelephone.getText().toString());
                data.put("filerEmail",ReporterEmail.getText().toString());
                data.put("FilerState",ReporterState.getText().toString());
                data.put("filerVillage",ReporterVillage.getText().toString());
                data.put("FilerPostalCode",ReporterPost.getText().toString());
                cref.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this,"Form Submitted Successfully",Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Submission Unsuccessful",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });











    }

    //CheckBox of Disease
    public void select(View view)
    {
        boolean checked=((CheckBox) view).isChecked();
        switch(view.getId())
        {
            case R.id.cbHepatic:
                if(checked)
                    arr[0]="on";
                else
                    arr[0]="";
                break;
            case R.id.cbRental:
                if(checked) {
                    arr[1] = "on";
                }
                else
                    arr[1]="";

                break;
            case R.id.cbCardiac:
                if(checked)
                    arr[2]="on";
                else
                    arr[2]="";
                break;
            case R.id.cbDiabetic:
                if(checked)
                    arr[3]="on";
                else
                    arr[3]="";
                break;
            case R.id.cbAnyOther:
                if(checked)
                    arr[4]="on";
                else
                    arr[4]="";
                break;
            default:

        }

    }
}
