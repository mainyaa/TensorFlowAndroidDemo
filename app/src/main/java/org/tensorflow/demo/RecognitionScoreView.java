/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import org.tensorflow.demo.Classifier.Recognition;

import java.util.List;

public class RecognitionScoreView extends View {
  private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint borderPaint;
  private final Paint bgPaint;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = getFrontText();
    borderPaint = getBorderText();

    bgPaint = new Paint();
    bgPaint.setColor(Color.BLACK);
    bgPaint.setAlpha(90);
    bgPaint.setAntiAlias(true);
  }

  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }
  private Paint getFrontText(){
    Paint text = new Paint();
    text.setTextSize(textSizePx);
    text.setColor(Color.WHITE);
    text.setAntiAlias(true);
    return text;
  }
  private Paint getBorderText(){
    Paint border = new Paint();
    border.setTextSize(textSizePx);
    border.setColor(Color.BLACK);
    border.setStyle(Paint.Style.STROKE);
    border.setAntiAlias(true);
    border.setStrokeWidth(5);
    return border;
  }

  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 40;
    int y = (int) (fgPaint.getTextSize() * 1.5f);


    if (results != null) {
      for (final Recognition recog : results) {
        String confidence = ((int)(recog.getConfidence() * 100.0f)) + "%";
        String s = confidence;
        float w = fgPaint.measureText(s);
        float textSize = fgPaint.getTextSize();

        int padding = 20;
        RectF rectF = new RectF(x-padding, y-textSize-(padding/2), x+w+padding/2, y+padding);
        canvas.drawRoundRect(rectF, 10.0f, 10.0f, bgPaint);
        canvas.drawText(s, x, y, borderPaint);
        canvas.drawText(s, x, y, fgPaint);

        s = recog.getTitle();
        float w2 = fgPaint.measureText(s);
        textSize = fgPaint.getTextSize();
        float x2 = x+w+padding*1.5f;

        rectF = new RectF(x2, y-textSize-(padding/2), x2+w2+padding, y+padding);
        canvas.drawRoundRect(rectF, 10.0f, 10.0f, bgPaint);
        canvas.drawText(s, x+w+padding+20, y, borderPaint);
        canvas.drawText(s, x+w+padding+20, y, fgPaint);

        y += fgPaint.getTextSize() * 2f;
      }
    }
  }
}
