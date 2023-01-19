package toylanguage.toy_language_interpreter_gui.Model.Values;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.RefType;

public class RefValue implements IValue{
    private final int address;
    private final IType locationType;
    public RefValue(int address, IType locationType){
        this.address = address;
        this.locationType = locationType;
    }
    public int getAddress(){
        return address;
    }
    public IType getLocationType(){
        return locationType;
    }
    @Override
    public IType getType(){
        return new RefType(locationType);
    }
    @Override
    public IValue deepCopy(){
        return new RefValue(address, locationType.deepCopy());
    }
    @Override
    public String toString(){
        return "(" + address + "," + locationType + ")";
    }

}
