
#include "cscore_cpp.h"
#include "c_util.h"

#include "UsbCameraImplWindows.h"
#include "PropertyImpl.h"
#include <memory>

#include "Log.h"

#include "WindowsMessagePump.h"

#include <shlwapi.h>
#include <Windows.h>
#include <windowsx.h>
#include <mfidl.h>
#include <mfapi.h>

#include <wpi/SmallString.h>
#include <wpi/memory.h>
#include <wpi/raw_ostream.h>
#include <wpi/timestamp.h>

#include "Handle.h"
#include "JpegUtil.h"
#include "Log.h"
#include "Notifier.h"
#include "Telemetry.h"
#include "UsbUtil.h"
#include "c_util.h"
#include "cscore_cpp.h"

#include <iostream>

#include <codecvt>

#include <vector>
#include <string>

#include <mfidl.h>
#include <mfapi.h>
#include <Dbt.h>
#include <ks.h>
#include <ksmedia.h>

#pragma comment(lib, "Mfplat.lib")
#pragma comment(lib, "Mf.lib")
#pragma comment(lib, "mfuuid.lib")
#pragma comment(lib, "Ole32.lib")
#pragma comment(lib, "User32.lib")
#pragma comment(lib, "Mfreadwrite.lib")
#pragma comment(lib, "Shlwapi.lib")

using namespace cs;

namespace cs {

class SourceReaderCB : public IMFSourceReaderCallback
{
public:
    SourceReaderCB(HANDLE hEvent, HWND hwnd, UsbCameraImplWindows* cameraImpl) :
      m_nRefCount(1), m_hEvent(hEvent), m_bEOS(FALSE), m_hrStatus(S_OK), m_hwnd(hwnd), m_cameraImpl(cameraImpl)
    {
        InitializeCriticalSection(&m_critsec);
    }

    // IUnknown methods
    STDMETHODIMP QueryInterface(REFIID iid, void** ppv)
    {
        static const QITAB qit[] =
        {
            QITABENT(SourceReaderCB, IMFSourceReaderCallback),
            { 0 },
        };
        return QISearch(this, qit, iid, ppv);
    }
    STDMETHODIMP_(ULONG) AddRef()
    {
        return InterlockedIncrement(&m_nRefCount);
    }
    STDMETHODIMP_(ULONG) Release()
    {
        ULONG uCount = InterlockedDecrement(&m_nRefCount);
        if (uCount == 0)
        {
            delete this;
        }
        return uCount;
    }

    // IMFSourceReaderCallback methods
    STDMETHODIMP OnReadSample(HRESULT hrStatus, DWORD dwStreamIndex,
        DWORD dwStreamFlags, LONGLONG llTimestamp, IMFSample *pSample);

    STDMETHODIMP OnEvent(DWORD, IMFMediaEvent *)
    {
        return S_OK;
    }

    STDMETHODIMP OnFlush(DWORD)
    {
        return S_OK;
    }

public:
    HRESULT Wait(DWORD dwMilliseconds, BOOL *pbEOS)
    {
        *pbEOS = FALSE;

        DWORD dwResult = WaitForSingleObject(m_hEvent, dwMilliseconds);
        if (dwResult == WAIT_TIMEOUT)
        {
            return E_PENDING;
        }
        else if (dwResult != WAIT_OBJECT_0)
        {
            return HRESULT_FROM_WIN32(GetLastError());
        }

        *pbEOS = m_bEOS;
        return m_hrStatus;
    }

private:

    // Destructor is private. Caller should call Release.
    virtual ~SourceReaderCB()
    {
    }

    void NotifyError(HRESULT hr)
    {
        wprintf(L"Source Reader error: 0x%X\n", hr);
    }

private:
    long                m_nRefCount;        // Reference count.
    CRITICAL_SECTION    m_critsec;
    HANDLE              m_hEvent;
    BOOL                m_bEOS;
    HRESULT             m_hrStatus;
    HWND                m_hwnd;
    UsbCameraImplWindows* m_cameraImpl;

};

HRESULT SourceReaderCB::OnReadSample(
    HRESULT hrStatus,
    DWORD /* dwStreamIndex */,
    DWORD dwStreamFlags,
    LONGLONG llTimestamp,
    IMFSample *pSample      // Can be NULL
    )
{
    EnterCriticalSection(&m_critsec);

    std::cout << "Sample Read\n";

    if (SUCCEEDED(hrStatus))
    {
        if (pSample)
        {
            // Do something with the sample.
            wprintf(L"Frame @ %I64d\n", llTimestamp);

            IMFMediaBuffer* outputBuffer;
            pSample->ConvertToContiguousBuffer(&outputBuffer);
        }
    }
    else
    {
        // Streaming error.
        NotifyError(hrStatus);
    }

    if (MF_SOURCE_READERF_ENDOFSTREAM & dwStreamFlags)
    {
        // Reached the end of the stream.
        m_bEOS = TRUE;
    }
    m_hrStatus = hrStatus;

    LeaveCriticalSection(&m_critsec);
    SetEvent(m_hEvent);
    PostMessage(m_hwnd, 4488, 0, 0);
    return S_OK;
}

UsbCameraImplWindows::UsbCameraImplWindows(const wpi::Twine& name, const wpi::Twine& path)
    : SourceImpl{name},
      m_path{path.str()} {
        //std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
  std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
  m_widePath = utf8_conv.from_bytes(m_path.c_str());
}

UsbCameraImplWindows::~UsbCameraImplWindows() {
  //m_active = false;
  std::cout << "Starting Destructor\n";

  m_messagePump = nullptr;
  std::cout << "Destructed loop\n";
}

void UsbCameraImplWindows::SetProperty(int property, int value, CS_Status* status) {}
void UsbCameraImplWindows::SetStringProperty(int property, const wpi::Twine& value,
                        CS_Status* status) {}

// Standard common camera properties
void UsbCameraImplWindows::SetBrightness(int brightness, CS_Status* status) {

}
int UsbCameraImplWindows::GetBrightness(CS_Status* status) const { return 0; }
void UsbCameraImplWindows::SetWhiteBalanceAuto(CS_Status* status) {}
void UsbCameraImplWindows::SetWhiteBalanceHoldCurrent(CS_Status* status) {}
void UsbCameraImplWindows::SetWhiteBalanceManual(int value, CS_Status* status) {}
void UsbCameraImplWindows::SetExposureAuto(CS_Status* status) {}
void UsbCameraImplWindows::SetExposureHoldCurrent(CS_Status* status) {}
void UsbCameraImplWindows::SetExposureManual(int value, CS_Status* status) {}

bool UsbCameraImplWindows::SetVideoMode(const VideoMode& mode, CS_Status* status) { return true; }
bool UsbCameraImplWindows::SetPixelFormat(VideoMode::PixelFormat pixelFormat,
                    CS_Status* status) {return true;}
bool UsbCameraImplWindows::SetResolution(int width, int height, CS_Status* status) { return true; }
bool UsbCameraImplWindows::SetFPS(int fps, CS_Status* status) { return true; }

void UsbCameraImplWindows::NumSinksChanged() {}
void UsbCameraImplWindows::NumSinksEnabledChanged() {}

void UsbCameraImplWindows::Start() {
  // Kick off the message pump

  m_messagePump = std::make_unique<WindowsMessagePump>([this](HWND hwnd, UINT uiMsg, WPARAM wParam, LPARAM lParam){
    this->PumpMain(hwnd, uiMsg, wParam, lParam);
  });
}

bool UsbCameraImplWindows::CheckDeviceChange(WPARAM wParam, DEV_BROADCAST_HDR *pHdr, bool* connected) {
    DEV_BROADCAST_DEVICEINTERFACE *pDi = NULL;

    *connected = false;

    if (pHdr == NULL)
    {
        return false;
    }
    if (pHdr->dbch_devicetype != DBT_DEVTYP_DEVICEINTERFACE)
    {
        return false;
    }

    // Compare the device name with the symbolic link.

    pDi = (DEV_BROADCAST_DEVICEINTERFACE*)pHdr;

    std::cout << "Device change check\n";

    if (_stricmp(m_path.c_str(), pDi->dbcc_name) == 0)
    {
      std::cout << "Strcmp true\n";
        if (wParam == DBT_DEVICEARRIVAL) {
          *connected = true;
          std::cout <<"rettrue\n";
          return true;
        } else if (wParam == DBT_DEVICEREMOVECOMPLETE) {
          *connected = false;
          std::cout <<"rettrue\n";
          return true;
        }
    }
    return false;
}

void UsbCameraImplWindows::TryConnectCamera() {
  DeviceConnect();
}

void UsbCameraImplWindows::DisconnectCamera() {
  if (m_connectVerbose) SINFO("Disconnected from " << m_path);
  SafeRelease(&m_sourceReader);
  SafeRelease(&m_mediaSource);
  SetConnected(false);
}

void UsbCameraImplWindows::PumpMain(HWND hwnd, UINT uiMsg, WPARAM wParam, LPARAM lParam) {
  switch (uiMsg) {
    case WM_CLOSE:
      SafeRelease(&m_sourceReader);
      SafeRelease(&m_mediaSource);
      SafeRelease(&m_imageCallback);
      break;
    case WM_CREATE:
      // Pump Created and ready to go
      m_callbackEventHandle = CreateEvent(NULL, FALSE, FALSE, NULL);
      m_imageCallback = new SourceReaderCB(m_callbackEventHandle, hwnd, this);
      TryConnectCamera();
      break;
    case WM_DEVICECHANGE: {
        // Device potentially changed
        PDEV_BROADCAST_HDR parameter = reinterpret_cast<PDEV_BROADCAST_HDR>(lParam);
        bool connected = false;
        if (CheckDeviceChange(wParam, parameter, &connected)) {
          if (connected) {
            // Connected
            TryConnectCamera();
          } else {
            // Disconnected
            DisconnectCamera();
          }
        }
      }
      break;
    case 4488: // New image
        m_sourceReader->ReadSample(MF_SOURCE_READER_FIRST_VIDEO_STREAM,
            0, NULL, NULL, NULL, NULL);
      break;
    case WM_TIMER:
        // Reconnect timer
        TryConnectCamera();
      break;
  }
}

void UsbCameraImplWindows::CameraThreadMain() {
  // Register to respond to Device change events

  bool wasStreaming = false;
  m_streaming = false;

  // while (m_active) {
  //   // Device Connect
  //   if (!m_mediaSource) DeviceConnect();
  // }
  // std::cout << "Thread Died" << std::endl;
}

static IMFSourceReader* CreateSourceReader(IMFMediaSource* mediaSource, IMFSourceReaderCallback* callback) {
  HRESULT hr = S_OK;
    IMFAttributes *pAttributes = NULL;

    IMFSourceReader* sourceReader = NULL;

    hr = MFCreateAttributes(&pAttributes, 1);
    if (FAILED(hr))
    {
        goto done;
    }

    hr = pAttributes->SetUnknown(MF_SOURCE_READER_ASYNC_CALLBACK, callback);
    if (FAILED(hr))
    {
        goto done;
    }

    hr = pAttributes->SetUINT32(MF_SOURCE_READER_DISCONNECT_MEDIASOURCE_ON_SHUTDOWN, TRUE);
    if (FAILED(hr))
    {
        goto done;
    }

    hr = MFCreateSourceReaderFromMediaSource(mediaSource, pAttributes, &sourceReader);

done:
    SafeRelease(&pAttributes);
    return sourceReader;
}

static IMFMediaSource* CreateVideoCaptureDevice(LPCWSTR pszSymbolicLink)
{
    IMFAttributes *pAttributes = NULL;
    IMFMediaSource *pSource = NULL;

    HRESULT hr = MFCreateAttributes(&pAttributes, 2);

    // Set the device type to video.
    if (SUCCEEDED(hr))
    {
        hr = pAttributes->SetGUID(
            MF_DEVSOURCE_ATTRIBUTE_SOURCE_TYPE,
            MF_DEVSOURCE_ATTRIBUTE_SOURCE_TYPE_VIDCAP_GUID
            );
    }


    // Set the symbolic link.
    if (SUCCEEDED(hr))
    {
        hr = pAttributes->SetString(
            MF_DEVSOURCE_ATTRIBUTE_SOURCE_TYPE_VIDCAP_SYMBOLIC_LINK,
            pszSymbolicLink
            );
    }

    if (SUCCEEDED(hr))
    {
        hr = MFCreateDeviceSource(pAttributes, &pSource);
    }

    SafeRelease(&pAttributes);
    return pSource;
}

static HRESULT ConfigureDecoder(IMFSourceReader *pReader, DWORD dwStreamIndex)
{
    IMFMediaType *pNativeType = NULL;
    IMFMediaType *pType = NULL;

    // Find the native format of the stream.
    HRESULT hr = pReader->GetNativeMediaType(dwStreamIndex, 0, &pNativeType);
    if (FAILED(hr))
    {
        return hr;
    }

    GUID majorType, subtype;

    // Find the major type.
    hr = pNativeType->GetGUID(MF_MT_MAJOR_TYPE, &majorType);
    if (FAILED(hr))
    {
        goto done;
    }

    // Define the output type.
    hr = MFCreateMediaType(&pType);
    if (FAILED(hr))
    {
        goto done;
    }

    hr = pType->SetGUID(MF_MT_MAJOR_TYPE, majorType);
    if (FAILED(hr))
    {
        goto done;
    }

    // Select a subtype.
    if (majorType == MFMediaType_Video)
    {
        subtype= MFVideoFormat_RGB24;
    }
    else if (majorType == MFMediaType_Audio)
    {
        subtype = MFAudioFormat_PCM;
    }
    else
    {
        // Unrecognized type. Skip.
        goto done;
    }

    hr = pType->SetGUID(MF_MT_SUBTYPE, subtype);
    if (FAILED(hr))
    {
        goto done;
    }

    // Set the uncompressed format.
    hr = pReader->SetCurrentMediaType(dwStreamIndex, NULL, pType);
    if (FAILED(hr))
    {
        goto done;
    }

done:
    SafeRelease(&pNativeType);
    SafeRelease(&pType);
    return hr;
}

bool UsbCameraImplWindows::DeviceConnect() {
  if (m_mediaSource && m_sourceReader) return true;

  if (m_connectVerbose) SINFO("Connecting to USB camera on " << m_path);

  SDEBUG3("opening device");

  const wchar_t* path = m_widePath.c_str();
  m_mediaSource = CreateVideoCaptureDevice(path);

  if (!m_mediaSource) return false;

  m_sourceReader = CreateSourceReader(m_mediaSource, m_imageCallback);

  if (!m_sourceReader) {
    SafeRelease(&m_mediaSource);
    return false;
  }

  ConfigureDecoder(m_sourceReader, MF_SOURCE_READER_FIRST_VIDEO_STREAM);

  m_sourceReader->ReadSample(MF_SOURCE_READER_FIRST_VIDEO_STREAM,
            0, NULL, NULL, NULL, NULL);

  SetConnected(true);
  return true;
}

std::unique_ptr<PropertyImpl> UsbCameraImplWindows::CreateEmptyProperty(
      const wpi::Twine& name) const {
  return nullptr;
}

bool UsbCameraImplWindows::CacheProperties(CS_Status* status) const {
  return true;
}


std::vector<UsbCameraInfo> EnumerateUsbCameras(CS_Status* status) {
  std::vector<UsbCameraInfo> retval;

  // Ensure we are initialized by grabbing the message pump
  //GetMessagePump();


  IMFMediaSource* ppSource = NULL;
  std::wstring_convert<std::codecvt_utf8<wchar_t>> utf8_conv;
  IMFMediaSource *pSource = NULL;
  IMFAttributes *pAttributes = NULL;
  IMFActivate **ppDevices = NULL;

  // Create an attribute store to specify the enumeration parameters.
  HRESULT hr = MFCreateAttributes(&pAttributes, 1);
  if (FAILED(hr))
  {
    goto done;
  }

  // Source type: video capture devices
  hr = pAttributes->SetGUID(
    MF_DEVSOURCE_ATTRIBUTE_SOURCE_TYPE,
    MF_DEVSOURCE_ATTRIBUTE_SOURCE_TYPE_VIDCAP_GUID
  );
  if (FAILED(hr))
  {
    goto done;
  }

  // Enumerate devices.
  UINT32 count;
  hr = MFEnumDeviceSources(pAttributes, &ppDevices, &count);
  if (FAILED(hr))
  {
    goto done;
  }

  if (count == 0)
  {
    hr = E_FAIL;
    goto done;
  }

  for (UINT32 i = 0; i < count; i++) {
    UsbCameraInfo info;
    info.dev = i;
    WCHAR buf[512];
    ppDevices[i]->GetString(MF_DEVSOURCE_ATTRIBUTE_FRIENDLY_NAME, buf, sizeof(buf), NULL);
    info.name = utf8_conv.to_bytes(buf);
    ppDevices[i]->GetString(MF_DEVSOURCE_ATTRIBUTE_SOURCE_TYPE_VIDCAP_SYMBOLIC_LINK, buf, sizeof(buf), NULL);
    info.path = utf8_conv.to_bytes(buf);
    retval.emplace_back(std::move(info));
  }

done:
  SafeRelease(&pAttributes);

  for (DWORD i = 0; i < count; i++)
  {
    SafeRelease(&ppDevices[i]);
  }
  CoTaskMemFree(ppDevices);
  SafeRelease(&pSource);
  return retval;
}


CS_Source CreateUsbCameraDev(const wpi::Twine& name, int dev,
                             CS_Status* status) {

  auto devices = EnumerateUsbCameras(status);
  if (devices.size() > dev) {
    return CreateUsbCameraPath(name, devices[dev].path, status);
  } else {
    // Error
    return CS_INVALID_HANDLE;
  }
}

CS_Source CreateUsbCameraPath(const wpi::Twine& name, const wpi::Twine& path,
                              CS_Status* status) {
  auto source = std::make_shared<UsbCameraImplWindows>(name, path);
  auto handle = Sources::GetInstance().Allocate(CS_SOURCE_USB, source);
  Notifier::GetInstance().NotifySource(name, handle, CS_SOURCE_CREATED);
  // Start thread after the source created event to ensure other events
  // come after it.
  source->Start();
  return handle;
}

std::string GetUsbCameraPath(CS_Source source, CS_Status* status) {
  auto data = Sources::GetInstance().Get(source);
  if (!data || data->kind != CS_SOURCE_USB) {
    *status = CS_INVALID_HANDLE;
    return std::string{};
  }
  return static_cast<UsbCameraImplWindows&>(*data->source).GetPath();
}

}

extern "C" {

CS_UsbCameraInfo* CS_EnumerateUsbCameras(int* count, CS_Status* status) {
  auto cameras = cs::EnumerateUsbCameras(status);
  CS_UsbCameraInfo* out = static_cast<CS_UsbCameraInfo*>(
      wpi::CheckedMalloc(cameras.size() * sizeof(CS_UsbCameraInfo)));
  *count = cameras.size();
  for (size_t i = 0; i < cameras.size(); ++i) {
    out[i].dev = cameras[i].dev;
    out[i].path = ConvertToC(cameras[i].path);
    out[i].name = ConvertToC(cameras[i].name);
  }
  return out;
}

void CS_FreeEnumeratedUsbCameras(CS_UsbCameraInfo* cameras, int count) {
  if (!cameras) return;
  for (int i = 0; i < count; ++i) {
    std::free(cameras[i].path);
    std::free(cameras[i].name);
  }
  std::free(cameras);
}

}
