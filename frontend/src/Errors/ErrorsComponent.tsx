import { useSelector } from "react-redux";
import type { RootState } from "../Store/store";

function ErrorsComponent(){
    const selector = useSelector((state: RootState) => state.ERRORS_INFO);

    return (
        <div>
            {selector.errors.map((error, i) => {
                return (
                    <div key={i}>{error}</div>
                )
            })}
        </div>
    )
}

export default ErrorsComponent;