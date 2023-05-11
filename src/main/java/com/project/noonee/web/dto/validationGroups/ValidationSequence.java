package com.project.noonee.web.dto.validationGroups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({
    ValidationGroups.NotBlankGroup.class,
    ValidationGroups.SizeCheckGroup.class,
    ValidationGroups.PatternCheckGroup.class,
    Default.class
})
public interface ValidationSequence {

}
