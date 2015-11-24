package speedith.core.reasoning.automatic;

import speedith.core.reasoning.InferenceRule;
import speedith.core.reasoning.RuleApplicationException;
import speedith.core.reasoning.args.RuleArg;
import speedith.core.reasoning.args.SubDiagramIndexArg;
import speedith.core.reasoning.automatic.wrappers.SpiderDiagramWrapper;

/**
 * @author Sven Linker [s.linker@brighton.ac.uk]
 */
public class PossibleCombiningApplication extends PossibleRuleApplication {

    public PossibleCombiningApplication(SpiderDiagramWrapper target, InferenceRule<? super RuleArg> rule) {
        super(target, rule);
    }

    @Override
    public RuleArg getArg(int subgoalindex) throws RuleApplicationException {
        return new SubDiagramIndexArg(subgoalindex, getTarget().getOccurrenceIndex());
    }
}
