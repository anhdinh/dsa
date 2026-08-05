package sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 2, 5, 6, 7};

        // Gọi lần đầu: xử lý toàn bộ mảng, từ chỉ số 0 tới chỉ số cuối cùng.
        // Dùng length - 1 vì high là chỉ số CUỐI (tính vào đoạn), không phải kích thước mảng.
        sort(numbers, 0, numbers.length - 1);

        System.out.println(Arrays.toString(numbers));
    }

    /**
     * Sắp xếp tăng dần đoạn arr[low..high].
     *
     * @param arr  mảng gốc — KHÔNG bị cắt nhỏ, ta chỉ thay đổi phạm vi xử lý
     * @param low  chỉ số ĐẦU của đoạn đang xử lý (tính vào đoạn)
     * @param high chỉ số CUỐI của đoạn đang xử lý (tính vào đoạn)
     */
    public static void sort(int[] arr, int low, int high) {
        // ĐIỀU KIỆN DỪNG của đệ quy:
        //   low == high -> đoạn còn đúng 1 phần tử, mặc nhiên đã sắp xếp
        //   low >  high -> đoạn rỗng
        // Phải là >= chứ không phải >: nếu dùng > thì khi low == high, mid sẽ bằng
        // chính low và sort(arr, low, mid) gọi lại y hệt chính nó -> đệ quy vô hạn
        // -> StackOverflowError.
        if (low >= high) return;

        // mid = chỉ số GIỮA đoạn, là điểm cắt đôi.
        // Viết low + (high - low) / 2 thay vì (low + high) / 2 để tránh tràn số int
        // khi mảng rất lớn (low + high có thể vượt Integer.MAX_VALUE và thành số âm).
        int mid = low + (high - low) / 2;

        // BƯỚC 1 — nửa TRÁI: đoạn [low .. mid].
        // Khi lời gọi này kết thúc, arr[low..mid] chắc chắn ĐÃ được sắp xếp.
        sort(arr, low, mid);

        // BƯỚC 2 — nửa PHẢI: đoạn [mid+1 .. high].
        // Bắt đầu từ mid + 1 chứ không phải mid, nếu không arr[mid] sẽ nằm ở
        // cả hai nửa -> sai kết quả và đệ quy không bao giờ dừng.
        sort(arr, mid + 1, high);

        // BƯỚC 3 — TRỘN hai nửa đã sắp xếp lại thành một.
        // Truyền low và high (phạm vi mà LỜI GỌI NÀY phụ trách), tuyệt đối không
        // truyền 0 và arr.length - 1: làm vậy là bắt merge gộp cả những vùng
        // chưa hề được sắp xếp nằm ngoài đoạn [low..high].
        // Đây cũng là nơi việc sắp xếp thực sự diễn ra — hai bước chia ở trên
        // không hề đụng tới dữ liệu.
        merge(arr, low, mid, high);
    }

    /**
     * Gộp hai đoạn ĐÃ SẮP XẾP nằm sát nhau là arr[low..mid] và arr[mid+1..high]
     * thành một đoạn arr[low..high] có thứ tự tăng dần.
     */
    public static void merge(int[] arr, int low, int mid, int high) {
        // Mảng phụ chứa kết quả tạm.
        // Bắt buộc phải có: nếu ghi thẳng vào arr thì sẽ đè lên các giá trị cũ
        // mà ta vẫn còn cần để so sánh ở những bước sau.
        // Kích thước = số phần tử của đoạn = high - low + 1 (cộng 1 vì cả low lẫn high đều tính vào).
        int[] temp = new int[high - low + 1];

        int i = low;      // con trỏ chạy trên nửa TRÁI:  quét từ low   -> mid
        int j = mid + 1;  // con trỏ chạy trên nửa PHẢI:  quét từ mid+1 -> high
        int k = 0;        // vị trí ghi tiếp theo trong temp, luôn đếm từ 0

        // VÒNG CHÍNH: chạy khi CẢ HAI nửa đều còn phần tử chưa lấy.
        // Mỗi lượt so sánh phần tử đầu của hai nửa rồi lấy cái nhỏ hơn.
        // Chỉ con trỏ của phần tử được lấy mới dịch tới; con trỏ bên kia đứng yên
        // để giữ lại phần tử lớn hơn, đem so ở lượt sau.
        while (i <= mid && j <= high) {
            // Dùng <= chứ không phải <: khi hai phần tử BẰNG NHAU thì ưu tiên lấy
            // từ nửa trái, tức giữ nguyên thứ tự ban đầu của chúng.
            // Đây chính là thứ làm Merge Sort có tính "stable" (ổn định).
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];   // lấy từ nửa trái, dịch i sang phải
            } else {
                temp[k++] = arr[j++];   // lấy từ nửa phải, dịch j sang phải
            }
        }

        // Vòng chính dừng ngay khi MỘT trong hai nửa cạn phần tử, nửa còn lại
        // vẫn còn hàng chưa lấy -> phải chép nốt.
        // Chép thẳng, không cần so sánh, vì nửa đó vốn đã có thứ tự và mọi phần tử
        // còn lại của nó đều lớn hơn hoặc bằng phần tử vừa được lấy.
        // Chỉ ĐÚNG MỘT trong hai vòng dưới chạy; vòng kia sai điều kiện ngay từ đầu.
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= high) {
            temp[k++] = arr[j++];
        }

        // Chép kết quả từ temp về đúng chỗ cũ trong mảng gốc.
        // t chạy trên temp     : 0, 1, 2, ...
        // low + t là chỗ tương ứng trong arr: low, low+1, low+2, ...
        // Phải cộng bù low, KHÔNG viết arr[t]: temp đánh chỉ số từ 0, còn đoạn
        // đang xử lý bắt đầu ở vị trí low trong mảng gốc.
        for (int t = 0; t < temp.length; t++) {
            arr[low + t] = temp[t];
        }
    }

}
