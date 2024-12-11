package com.example.recieve_sms;

import android.content.BroadcastReceiver; // Base pour écouter les événements système
import android.content.Context; // Contexte de l'application
import android.content.Intent; // Intention pour transmettre les événements
import android.os.Bundle; // Pour récupérer les données transmises
import android.telephony.SmsMessage; // Représentation des messages SMS
import android.util.Log; // Pour les journaux
import android.widget.Toast; // Pour afficher des messages utilisateur

public class RecieveSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Récupération des données transmises par le système
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus"); // Extraction des messages
            if (pdus != null) {
                for (Object pdu : pdus) {
                    // Conversion des PDU en messages lisibles
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress(); // Expéditeur
                    String messageBody = smsMessage.getMessageBody(); // Contenu

                    // Affichage des informations reçues
                    Toast.makeText(context, "SMS reçu de: " + sender + " - Message: " + messageBody, Toast.LENGTH_LONG).show();
                    Log.d("SmsReceiver", "SMS reçu de: " + sender + " - Message: " + messageBody);
                }
            }
        }
    }
}
