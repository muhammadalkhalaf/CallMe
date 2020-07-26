package kh.muhammed.callme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneCallReceiver extends BroadcastReceiver {

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction() == null) return;
        if (!intent.getAction().equals("android.intent.action.PHONE_STATE")) return;

        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephony == null) return;
        telephony.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                handleOnCallStateChanged(context, state, phoneNumber);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void handleOnCallStateChanged(Context context, int state, String phoneNumber) {
        if (lastState == state) {
            return;
        }
        if (state == TelephonyManager.CALL_STATE_RINGING) {
            openIncomingCallActivity(context, phoneNumber);
        }
        lastState = state;
    }

    private void openIncomingCallActivity(final Context context, String phoneNumber) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, IncomingCallActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }, 500);

        Toast.makeText(context, "openIncomingCallActivity " + phoneNumber, Toast.LENGTH_LONG).show();
    }
}