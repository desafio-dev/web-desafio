import MenuBar from "../../components/menu"
import Owner from "../../components/owner"
import UploadFile from "../../components/uploadFile"

export default function Home() {
    

    return (
        <>
            <MenuBar />

            <div style={{ margin: '16px' }}>
                <UploadFile />
                <Owner />
            </div>
        </>
    )
}