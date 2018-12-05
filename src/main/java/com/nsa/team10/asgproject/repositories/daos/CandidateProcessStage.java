package com.nsa.team10.asgproject.repositories.daos;

public enum CandidateProcessStage
{
    MAKE_PAYMENT(1),
    AWAITING_GS_ASSIGNMENT(2),
    AWAITING_GS_RESULT(3),
    SUBMIT_OP_MANUAL(4),
    AWAITING_OP_MANUAL_RESULT(5),
    AWAITING_FLIGHT_ASSESSMENT(6),
    AWAITING_FLIGHT_ASSESSMENT_RESULT(7),
    AWAITING_RECOMMENDATION(8),
    FEEDBACK_COLLECTION(9),
    COMPLETED(10);

    private int stageId;

    CandidateProcessStage(int stageId) {
        this.stageId = stageId;
    }

    public int getStageId() {
        return stageId;
    }
}