package com.gui.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAssessmentCustomer {
    private List<ApprovedCard> cards;
}
