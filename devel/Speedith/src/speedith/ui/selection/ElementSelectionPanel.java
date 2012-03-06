/*
 *   Project: Speedith
 * 
 * File name: ElementSelectionPanel.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2012 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package speedith.ui.selection;

import icircles.gui.CirclesPanel2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import speedith.core.lang.NullSpiderDiagram;
import speedith.core.lang.SpiderDiagram;
import speedith.core.lang.SpiderDiagrams;
import static speedith.i18n.Translations.*;
import speedith.ui.SpiderDiagramClickEvent;
import speedith.ui.selection.SelectionStep.ClickRejectionExplanation;

/**
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class ElementSelectionPanel extends javax.swing.JPanel {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private final SelectionSequenceMutable selection;
    private int curStep = 0;
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Initialises the selection panel with a {@link NullSpiderDiagram} and an
     * omni-select step.
     */
    public ElementSelectionPanel() {
        this(SpiderDiagrams.createNullSD(), new SelectionStepAny(), new SelectionStepAny());
    }

    /**
     * Initialises this selection panel with the given selection steps. <p><span
     * style="font-weight:bold">Note</span>: this constructor makes a copy of
     * the given array.</p>
     *
     * @param diagram the diagram from which to select elements.
     * @param selectionSteps the selection steps this panel should display to
     * the user.
     */
    public ElementSelectionPanel(SpiderDiagram diagram, SelectionStep... selectionSteps) {
        this(diagram, selectionSteps == null || selectionSteps.length == 0 ? null : new ArrayList<SelectionStep>(Arrays.asList(selectionSteps)));
    }

    /**
     * Initialises this selection panel with the given selection steps. <p><span
     * style="font-weight:bold">Note</span>: this constructor makes a copy of
     * the given collection.</p>
     *
     * @param diagram the diagram from which to select elements.
     * @param selectionSteps the selection steps this panel should display to
     * the user.
     */
    public ElementSelectionPanel(SpiderDiagram diagram, Collection<SelectionStep> selectionSteps) {
        this(diagram, selectionSteps == null || selectionSteps.isEmpty() ? null : new ArrayList<SelectionStep>(selectionSteps));
    }

    /**
     * Initialises this selection panel with the given selection steps. <p><span
     * style="font-weight:bold">Note</span>: this constructor does not make a
     * copy of the given array list.</p>
     *
     * @param diagram the diagram from which to select elements.
     * @param selectionSteps the selection steps this panel should display to
     * the user.
     */
    ElementSelectionPanel(SpiderDiagram diagram, ArrayList<SelectionStep> selectionSteps) {
        initComponents();
        if (diagram == null) {
            throw new IllegalArgumentException("The argument 'diagram' must not be null.");
        }
        if (selectionSteps == null || selectionSteps.isEmpty()) {
            throw new IllegalArgumentException(speedith.core.i18n.Translations.i18n("GERR_EMPTY_ARGUMENT", "selectionSteps"));
        }
        // The collection of selection steps must not contain any null values.
        for (SelectionStep selectionStep : selectionSteps) {
            if (selectionStep == null) {
                throw new IllegalArgumentException(i18n("SELSEQ_NULL_STEPS"));
            }
        }
        this.selection = new SelectionSequenceMutable(selectionSteps);
        this.spiderDiagramPanel.setDiagram(diagram);

        // Some extra initialisation:
        refreshUI();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Autogenerated Code">
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spiderDiagramPanel = new speedith.ui.SpiderDiagramPanel();
        errorMessage = new javax.swing.JLabel();
        stepInstructionMessage = new javax.swing.JLabel();
        stepNumber = new javax.swing.JLabel();
        finishButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectionList = new javax.swing.JList();
        selectionLabel = new javax.swing.JLabel();

        spiderDiagramPanel.addSpiderDiagramClickListener(new speedith.ui.SpiderDiagramClickListener() {
            public void spiderDiagramClicked(speedith.ui.SpiderDiagramClickEvent evt) {
                onSpiderDiagramClicked(evt);
            }
        });

        errorMessage.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        errorMessage.setForeground(new java.awt.Color(204, 0, 0));
        errorMessage.setText("* Error message...");

        stepInstructionMessage.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        stepInstructionMessage.setText("Step instruction message...");

        stepNumber.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        stepNumber.setText("Step 1 of 5: ");

        finishButton.setMnemonic(i18n("GSTR_FINISH_BUTTON_MNEMONIC").charAt(0));
        finishButton.setText(i18n("GSTR_FINISH_BUTTON_TEXT")); // NOI18N
        finishButton.setEnabled(false);
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        clearButton.setMnemonic(i18n("GSTR_CLEAR_BUTTON_MNEMONIC").charAt(0));
        clearButton.setText(i18n("GSTR_CLEAR_BUTTON_TEXT")); // NOI18N
        clearButton.setToolTipText(i18n("SELSEQ_CLEAR_SELECTION")); // NOI18N
        clearButton.setEnabled(false);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        nextButton.setMnemonic(i18n("GSTR_NEXT_BUTTON_MNEMONIC").charAt(0));
        nextButton.setText(i18n("GSTR_NEXT_BUTTON_TEXT")); // NOI18N
        nextButton.setEnabled(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        cancelButton.setMnemonic(i18n("GSTR_CANCEL_BUTTON_MNEMONIC").charAt(0));
        cancelButton.setText(i18n("GSTR_CANCEL_BUTTON_TEXT")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        previousButton.setMnemonic(i18n("GSTR_PREVIOUS_BUTTON_MNEMONIC").charAt(0));
        previousButton.setText(i18n("GSTR_PREVIOUS_BUTTON_TEXT")); // NOI18N
        previousButton.setEnabled(false);
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        selectionList.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(selectionList);

        selectionLabel.setText("Selection:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(finishButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(previousButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton))
            .addComponent(errorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(stepNumber)
                .addGap(1, 1, 1)
                .addComponent(stepInstructionMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(spiderDiagramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spiderDiagramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepNumber)
                    .addComponent(stepInstructionMessage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(nextButton)
                    .addComponent(clearButton)
                    .addComponent(finishButton)
                    .addComponent(previousButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finishButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearCurStepSelection(true, true);
    }//GEN-LAST:event_clearButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        goToNextStep(true, true);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        goToPreviousStep(true);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void onSpiderDiagramClicked(speedith.ui.SpiderDiagramClickEvent evt) {//GEN-FIRST:event_onSpiderDiagramClicked
        SelectionStep curSelStep = getCurSelStep();
        if (curSelStep != null && !curSelStep.isFinished(selection, curStep)) {
            ClickRejectionExplanation result = curSelStep.acceptClick(evt, selection, getCurrentStep());
            if (result == null) {
                selection.addAcceptedClick(getCurrentStep(), evt);
                // Check if the step is finished. If it is, go to the next one:
                goToNextStep(false, false);
                refreshUI();
            }
        }
    }//GEN-LAST:event_onSpiderDiagramClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JButton finishButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel selectionLabel;
    private javax.swing.JList selectionList;
    private speedith.ui.SpiderDiagramPanel spiderDiagramPanel;
    private javax.swing.JLabel stepInstructionMessage;
    private javax.swing.JLabel stepNumber;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Properties">
    /**
     * Returns the current selection.
     *
     * @return the current selection.
     */
    public SelectionSequence getSelection() {
        return selection;
    }

    /**
     * @return the index of the current step.
     */
    public int getCurrentStep() {
        return curStep;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="UI Refresh Methods">
    private void refreshStepInstructionLabel() {
        if (getCurrentStep() >= getStepCount()) {
            stepInstructionMessage.setText(null);
        } else {
            String instruction = selection.getSelectionStepAt(getCurrentStep()).getInstruction();
            stepInstructionMessage.setText(instruction);
        }
    }

    private void refreshStepLabel() {
        if (getCurrentStep() >= getStepCount()) {
            stepNumber.setText(i18n("SELSEQ_STEP_FINISHED"));
        } else if (getStepCount() > 1) {
            stepNumber.setText(i18n("SELSEQ_STEP_N_OF_M", getCurrentStep() + 1, selection.getSelectionStepsCount()));
        } else {
            stepNumber.setText(null);
        }
    }

    private void refreshUI() {
        refreshAllButtons();
        refreshDiagramPanel();
        refreshAllLabels();
    }

    private void setErrorMsg(String msg) {
        if (msg == null || msg.isEmpty()) {
            errorMessage.setText(null);
        } else {
            errorMessage.setText(i18n("SELSEQ_ERROR_MSG", msg));
        }
    }

    private void refreshAllLabels() {
        refreshStepInstructionLabel();
        refreshStepLabel();
        setErrorMsg(null);
    }

    private void refreshAllButtons() {
        refreshNextButton();
        refreshPreviousButton();
        refreshFinishButton();
        refreshClearButton();
    }

    private void refreshNextButton() {
        SelectionStep curSelStep = getCurSelStep();
        nextButton.setEnabled(getCurrentStep() < getStepCount() - 1 && curSelStep.isSkippable(selection, getCurrentStep()));
    }

    /**
     * The user can finish the selection only if all steps are skippable.
     */
    private void refreshFinishButton() {
        boolean canFinish = true;
        for (int i = getCurrentStep(); i < getStepCount(); i++) {
            SelectionStep selStep = selection.getSelectionStepAt(i);
            if (!selStep.isSkippable(selection, i)) {
                canFinish = false;
                break;
            }
        }
        finishButton.setEnabled(canFinish);
    }

    private void refreshPreviousButton() {
        previousButton.setEnabled(getCurrentStep() > 0);
    }

    private void refreshClearButton() {
        clearButton.setEnabled(getCurSelStep() != null && selection.getAcceptedClickCount(getCurrentStep()) > 0);
    }

    private void refreshDiagramPanel() {
        // Disable highlighting in the diagram, if the whole thing is finished:
        spiderDiagramPanel.setHighlightMode(getCurSelStep() == null || getCurrentStep() >= getStepCount() ? CirclesPanel2.None : getCurSelStep().getHighlightingMode());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Internal Logic Methods">
    private void clearCurStepSelection(boolean force, boolean refreshUIIfChange) {
        // Check whether we should clean the selection on starting this step?
        SelectionStep curSelStep = getCurSelStep();
        if (curSelStep != null && (force || curSelStep.cleanSelectionOnStart())) {
            selection.clearAcceptedClicks(getCurrentStep());
            if (refreshUIIfChange) {
                refreshUI();
            }
        }
    }

    private void goToNextStep(boolean skip, boolean refreshUIIfNext) {
        SelectionStep curSelStep = getCurSelStep();
        if (curSelStep != null && (skip || curSelStep.isFinished(selection, getCurrentStep()))) {
            ++curStep;
            curSelStep = getCurSelStep();
            clearCurStepSelection(false, false);
            if (refreshUIIfNext) {
                refreshUI();
            }
        }
    }

    private void goToPreviousStep(boolean refreshUIIfChange) {
        if (getCurrentStep() > 0) {
            --curStep;
            SelectionStep curSelStep = getCurSelStep();
            clearCurStepSelection(false, false);
            if (refreshUIIfChange) {
                refreshUI();
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Properties">
    private SelectionStep getCurSelStep() {
        return getCurrentStep() >= getStepCount() ? null : selection.getSelectionStepAt(getCurrentStep());
    }

    private int getStepCount() {
        return selection.getSelectionStepsCount();
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Helper Classes">
    private static class SelectionSequenceMutable extends SelectionSequence {

        public SelectionSequenceMutable(ArrayList<SelectionStep> selectionSteps) {
            super(selectionSteps);
        }

        void addAcceptedClick(int stepIndex, SpiderDiagramClickEvent click) {
            (acceptedSelections[stepIndex] == null
                    ? (acceptedSelections[stepIndex] = new ArrayList<SpiderDiagramClickEvent>())
                    : acceptedSelections[stepIndex]).add(click);
        }

        void clearAcceptedClicks(int stepIndex) {
            if (acceptedSelections[stepIndex] != null) {
                acceptedSelections[stepIndex].clear();
            }
        }
    }
    //</editor-fold>
}
