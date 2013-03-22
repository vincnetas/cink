/**
 * 
 */
package com.xmedic.cink.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * @author vincentas
 *
 */
public class Timer extends AsyncTask<Long, Long, Void> {

		private DateFormat dateFormat = new SimpleDateFormat("mm:ss", Locale.US);
				
		private TextView textView;
		
		public Timer(TextView textView) {
			this.textView = textView;
		}
		
		@Override
		protected Void doInBackground(Long... params) {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < params[0] && !isCancelled()) {
				long timeLeft = params[0] - (System.currentTimeMillis() - start);
				publishProgress(timeLeft);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			
			return null;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */
		@Override
		protected void onProgressUpdate(Long... values) {
			setTime(values[0]);
		}
		
		private void setTime(long time) {
			textView.setText(dateFormat.format(new Date(time)));
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Void result) {
			setTime(0);
		}			
};

